package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountExtractor implements ResultSetExtractor<Account> {

  @Override
  public Account extract(ResultSet resultSet) throws SQLException {
    Account account = new Account();
    account.setId(resultSet.getInt("acc.id"));
    account.setEmail(resultSet.getString("acc.email"));
    account.setAddress(resultSet.getString("acc.address"));
    account.setUsername(resultSet.getString("acc.username"));
    account.setPassword(resultSet.getString("acc.password"));
    account.setLastName(resultSet.getString("acc.lastname"));
    account.setFirstName(resultSet.getString("acc.firstname"));
    account.setAdmin(resultSet.getBoolean("acc.admin"));
    return account;
  }
}
