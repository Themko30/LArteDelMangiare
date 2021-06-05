package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryExtractor implements ResultSetExtractor<Country> {

  @Override
  public Country extract(ResultSet resultSet) throws SQLException {
    Country country = new Country();
    country.setId(resultSet.getInt("cou.id"));
    country.setLabel(resultSet.getString("cou.label"));
    return country;
  }
}
