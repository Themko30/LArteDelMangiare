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

  protected SqlCategoryDAO(DataSource source) {
    super(source);
  }

  @Override
  public List<Category> fetchCategories() throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("category", "cat");
      queryBuilder.select();
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
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
  public boolean createCategories(Category category) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("category", "cat");
      queryBuilder.insert("label");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setString(1, category.getLabel());
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
  public Optional<Category> fetchCategoriesWithProducts(int categoryId) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("category", "cat");
      queryBuilder
          .select()
          .innerJoin("products", "pro")
          .on("cat.id = pro.category_fk")
          .where("cat.id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, categoryId);
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