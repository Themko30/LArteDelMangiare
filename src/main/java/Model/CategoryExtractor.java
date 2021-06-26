package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryExtractor implements ResultSetExtractor<Category> {

  @Override
  public Category extract(ResultSet resultSet) throws SQLException {
    Category category = new Category();
    category.setId(resultSet.getInt("cat.id"));
    category.setLabel(resultSet.getString("cat.label"));
    category.setDescription(resultSet.getString("cat.description"));
    category.setImage(resultSet.getString("cat.image"));
    return category;
  }
}
