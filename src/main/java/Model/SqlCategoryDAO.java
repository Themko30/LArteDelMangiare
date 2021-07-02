package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlCategoryDAO extends SqlDao implements CategoryDao<SQLException> {

  public SqlCategoryDAO(DataSource source) {
    super(source);
  }

  @Override
  public List<Category> fetchCategories(Paginator paginator) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("category", "cat");
      String query = queryBuilder.select().limit(true).generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, paginator.getOffset());
        ps.setInt(2, paginator.getLimit());
        ResultSet set = ps.executeQuery();
        CategoryExtractor categoryExtractor = new CategoryExtractor();
        List<Category> categories = new ArrayList<>();
        while (set.next()) {
          categories.add(categoryExtractor.extract(set));
        }
        return categories;
      }
    }
  }

  @Override
  public Optional<Category> fetchCategory(int id) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("category", "cat");
      String query = queryBuilder.select().where("cat.id=?").generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        Category category = null;
        if (set.next()) {
          category = new CategoryExtractor().extract(set);
        }
        return Optional.ofNullable(category);
      }
    }
  }

  @Override
  public int countAll() throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = ("SELECT COUNT(*) FROM category AS cat ");
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
  public boolean createCategories(Category category) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("category", "cat");
      queryBuilder.insert("id", "label", "description", "image");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, category.getId());
        ps.setString(2, category.getLabel());
        ps.setString(3, category.getDescription());
        ps.setString(4, category.getImage());
        int row = ps.executeUpdate();
        return row == 1;
      }
    }
  }

  @Override
  public boolean updateCategories(Category category) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("category", "cat");
      queryBuilder.update("label").where("id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setString(1, category.getLabel());
        ps.setInt(2, category.getId());
        int row = ps.executeUpdate();
        return row == 1;
      }
    }
  }

  @Override
  public Optional<Category> fetchCategoriesWithProducts(int categoryId, Paginator paginator)
      throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("category", "cat");
      queryBuilder
          .select()
          .limit(true)
          .innerJoin("products", "pro")
          .on("cat.id = pro.category_fk")
          .where("cat.id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, paginator.getOffset());
        ps.setInt(2, paginator.getLimit());
        ps.setInt(3, categoryId);
        ResultSet set = ps.executeQuery();
        CategoryExtractor categoryExtractor = new CategoryExtractor();
        Category category = null;
        if (set.next()) {
          category = categoryExtractor.extract(set);
          category.setProducts(new ArrayList<>());
          ProductExtractor productExtractor = new ProductExtractor();
          category.getProducts().add(productExtractor.extract(set));
          while (set.next()) {
            category.getProducts().add(productExtractor.extract(set));
          }
        }
        return Optional.ofNullable(category);
      }
    }
  }
}
