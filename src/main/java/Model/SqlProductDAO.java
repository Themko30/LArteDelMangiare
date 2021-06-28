package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlProductDAO extends SqlDao implements ProductDao<SQLException> {

  public SqlProductDAO(DataSource source) {
    super(source);
  }

  @Override
  public List<Product> fetchProducts(Paginator paginator) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("products", "pro");
      String query = queryBuilder.select().limit(true).generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, paginator.getOffset());
        ps.setInt(2, paginator.getLimit());
        ResultSet set = ps.executeQuery();
        ProductExtractor productExtractor = new ProductExtractor();
        List<Product> products = new ArrayList<>();
        while (set.next()) {
          products.add(productExtractor.extract(set));
        }
        return products;
      }
    }
  }

  @Override
  public int countAll() throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = ("SELECT COUNT(*) FROM products AS pro ");
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
  public Optional<Product> fetchProduct(int id) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("products", "pro");
      String query = queryBuilder.select().where("pro.id=?").generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        Product product = null;
        if (set.next()) {
          product = new ProductExtractor().extract(set);
        }
        return Optional.ofNullable(product);
      }
    }
  }

  @Override
  public Optional<Product> fetchProductByLabel(String label) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("products", "pro");
      String query = queryBuilder.select().where("pro.label=?").generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, label);
        ResultSet set = ps.executeQuery();
        Product product = null;
        if (set.next()) {
          product = new ProductExtractor().extract(set);
        }
        return Optional.ofNullable(product);
      }
    }
  }

  @Override
  public boolean createProduct(Product product) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("products", "pro");
      queryBuilder.insert(" id", "prodname", "quantity", "price", "label", "image");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, product.getId());
        ps.setString(2, product.getProdName());
        ps.setInt(3, product.getQuantity());
        ps.setDouble(4, product.getPrice());
        ps.setString(5, product.getLabel());
        ps.setString(6, product.getImage());
        int row = ps.executeUpdate();
        return row == 1;
      }
    }
  }

  @Override
  public boolean updateProduct(Product product) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("products", "pro");
      queryBuilder.update("prodname", " quantity", " price", " label", " image").where(" id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setString(1, product.getProdName());
        ps.setInt(2, product.getQuantity());
        ps.setDouble(3, product.getPrice());
        ps.setString(4, product.getLabel());
        ps.setString(5, product.getImage());
        ps.setInt(6, product.getId());
        int rows = ps.executeUpdate();
        return rows == 1;
      }
    }
  }

  @Override
  public boolean deleteProduct(int id) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("products", "pro");
      queryBuilder.delete().where(" id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        return rows == 1;
      }
    }
  }
}
