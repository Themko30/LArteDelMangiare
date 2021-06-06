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

  protected SqlPurchaseDAO(DataSource source) {
    super(source);
  }

  @Override
  public List<Purchase> fetchPurchases(int start, int end) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("purchase", "pur");
      String query = queryBuilder.select().limit(true).generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, start);
        ps.setInt(2, end);
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
      QueryBuilder queryBuilder = new QueryBuilder("purchase_products", "pp");
      StringBuilder builder = new StringBuilder();
      // First join clause in order to join purchase on idpurchase
      queryBuilder.select().innerJoin("purchase", "pur").on("pp.idpurchase = pur.id");
      // Second join clause in order to join products on idproducts
      queryBuilder.innerJoin("products", "pro").on("pp.idpruducts = pro.id");
      // fetching country
      queryBuilder.outerJoin(true, "country", "cou").on("pro.country_fk = cou.id");
      // fetching category
      queryBuilder.outerJoin(true, "category", "cat").on("pro.category_fk = cat.id");
      // where condition
      queryBuilder.where(" pur.account_fk = ?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
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
      QueryBuilder queryBuilder = new QueryBuilder("purchase", "pur");
      String query = queryBuilder.select().where("pur.id=?").generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        Purchase purchase = null;
        if (set.next()) {
          purchase = new PurchaseExtractor().extract(set);
        }
        return Optional.ofNullable(purchase);
      }
    }
  }

  @Override
  public boolean createPurchase(Purchase purchase) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("purchase", "pur");
      queryBuilder.insert("id", "card_circuit", "pan_card", "date", "total");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, purchase.getId());
        ps.setString(2, purchase.getCardCircuit());
        ps.setLong(3, purchase.getPanCard());
        ps.setDate(4, Date.valueOf(purchase.getCreated()));
        int row = ps.executeUpdate();
        return row == 1;
      }
    }
  }
}
