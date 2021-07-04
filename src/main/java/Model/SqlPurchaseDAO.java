package Model;

import java.sql.Connection;
import java.sql.Date;
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

  public SqlPurchaseDAO(DataSource source) {
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
  public List<Purchase> fetchPurchasesWithProducts(int accountId) throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = PurchaseQuery.fetchPurchasesWithProducts();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, accountId);
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
            purchaseMap
                .get(purchaseId)
                .getCart()
                .addProduct(productExtractor.extract(set), set.getInt("pp.quantity"));
          } else {
            purchaseMap
                .get(purchaseId)
                .getCart()
                .addProduct(productExtractor.extract(set), set.getInt("pp.quantity"));
          }
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
  public int countAll() throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = ("SELECT COUNT(*) FROM purchase AS pur ");
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ResultSet set = ps.executeQuery();
        int size = 0;
        if (set.next()) {
          size = set.getInt("COUNT(*)");
        }
        return size;
      }
    }
  }

  @Override
  public int sum() throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = ("SELECT SUM(total) total FROM purchase as pur");
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ResultSet set = ps.executeQuery();
        int size = 0;
        if (set.next()) {
          size = set.getInt("total");
        }
        return size;
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
          PreparedStatement psAssoc = conn.prepareStatement(query2)) {
        int rows = ps.executeUpdate();
        int total = rows;
        for (CartItem item : purchase.getCart().getItems()) {
          psAssoc.setInt(1, item.getProduct().getId());
          psAssoc.setInt(2, purchase.getId());
          psAssoc.setInt(3, item.getQuantity());
          total += psAssoc.executeUpdate();
        }
        if (total == (rows + purchase.entries())) {
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

  @Override
  public boolean createPurchaseAdmin(Purchase purchase) throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = PurchaseQuery.createPurchase();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, purchase.getCardCircuit());
        ps.setLong(2, purchase.getPanCard());
        ps.setDate(3, Date.valueOf(purchase.getCreated()));
        ps.setDouble(4, purchase.getTotal());
        ps.setInt(5, purchase.getAccountNum());
        int row = ps.executeUpdate();
        return row == 1;
      }
    }
  }

  @Override
  public boolean updatePurchaseAdmin(Purchase purchase) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("purchase", "pur");
      queryBuilder.update("card_circuit", "pan_card", "date", "total", "account_fk").where("id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setString(1, purchase.getCardCircuit());
        ps.setLong(2, purchase.getPanCard());
        ps.setDate(3, Date.valueOf(purchase.getCreated()));
        ps.setDouble(4, purchase.getTotal());
        ps.setInt(5, purchase.getAccountNum());
        ps.setInt(6, purchase.getId());
        int rows = ps.executeUpdate();
        return rows == 1;
      }
    }
  }
}
