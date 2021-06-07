package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlPurchaseDAO extends SqlDao implements PurchaseDao<SQLException> {

  protected SqlPurchaseDAO(DataSource source) {
    super(source);
  }

  @Override
  public List<Purchase> fetchPurchases(Paginator paginator) throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = PurchaseQuery.fetchPurchases();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, paginator.getOffset());
        ps.setInt(2, paginator.getLimit());
        ResultSet set = ps.executeQuery();
        PurchaseExtractor purchaseExtractor = new PurchaseExtractor();
        List<Purchase> purchases = new ArrayList<>();
        while (set.next()) {
          purchases.add(purchaseExtractor.extract(set));
        }
        return purchases;
      }
    }
  }

  @Override
  public List<Purchase> fetchPurchasesWithProducts(int accountId, Paginator paginator)
      throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = PurchaseQuery.fetchPurchasesWithProducts();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, accountId);
        ps.setInt(2, paginator.getOffset());
        ps.setInt(3, paginator.getLimit());
        ResultSet set = ps.executeQuery();
        Map<Integer, Purchase> purchaseMap = new LinkedHashMap<>();
        PurchaseExtractor purchaseExtractor = new PurchaseExtractor();
        ProductExtractor productExtractor = new ProductExtractor();
        CategoryExtractor categoryExtractor = new CategoryExtractor();
        CountryExtractor countryExtractor = new CountryExtractor();
        while (set.next()) {
          int purchaseId = set.getInt("pur.id");
          if (!purchaseMap.containsKey(purchaseId)) {
            Purchase purchase = purchaseExtractor.extract(set);
            purchase.setCart(new Cart(new ArrayList<>()));
            purchaseMap.put(purchaseId, purchase);
          }
          Product product = productExtractor.extract(set);
          Category category = categoryExtractor.extract(set);
          Country country = countryExtractor.extract(set);
          product.setCategory(category);
          product.setCountry(country);
          purchaseMap.get(purchaseId).getCart().addProducts(product, set.getInt("pp.quantity"));
        }
        return new ArrayList<>(purchaseMap.values());
      }
    }
  }

  @Override
  public Optional<Purchase> fetchPurchase(int id) throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = PurchaseQuery.fetchPurchase();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        Purchase purchase = set.next() ? new PurchaseExtractor().extract(set) : null;
        return Optional.ofNullable(purchase);
      }
    }
  }

  @Override
  public boolean createPurchase(Purchase purchase) throws SQLException {
    try (Connection conn = source.getConnection()) {
      conn.setAutoCommit(false);
      String query = PurchaseQuery.createPurchase();
      String query2 = PurchaseQuery.insertCart();
      try (PreparedStatement ps = conn.prepareStatement(query);
          PreparedStatement psAssoc = conn.prepareStatement(query2); ) {
        int rows = ps.executeUpdate();
        int total = rows;
        for (CartItem item : purchase.getCart().getItems()) {
          psAssoc.setInt(1, item.getProduct().getId());
          psAssoc.setInt(2, purchase.getId());
          psAssoc.setInt(3, item.getQuantity());
          total += psAssoc.executeUpdate();
        }
        if (total == (rows + purchase.entries())) { // DA VEDERE LA RISPOSTA DEL PROF
          conn.commit();
          conn.setAutoCommit(true);
          return true;
        } else {
          conn.rollback();
          conn.setAutoCommit(true);
          return false;
        }
      }
    }
  }
}
