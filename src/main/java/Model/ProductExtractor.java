package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExtractor implements ResultSetExtractor<Product> {

  @Override
  public Product extract(ResultSet resultSet) throws SQLException {
    Product product = new Product();
    product.setId(resultSet.getInt("pro.id"));
    product.setProdName(resultSet.getString("pro.prodname"));
    product.setQuantity(resultSet.getInt("pro.quantity"));
    product.setPrice(resultSet.getDouble("pro.price"));
    product.setLabel(resultSet.getString("pro.label"));
    product.setImage(resultSet.getString("pro.image"));
    return product;
  }
}
