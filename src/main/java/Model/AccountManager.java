package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class AccountManager extends Manager implements AccountDao {

  private static final AccountQuery QUERY = new AccountQuery("account");

  protected AccountManager(DataSource source) {
    super(source);
  }

  @Override
  public List<Account> fetchAccounts(int start, int end) throws SQLException {
    try (Connection conn = source.getConnection()) {
      try (PreparedStatement ps = conn.prepareStatement(QUERY.selectAccount())) {
        ps.setInt(1, start);
        ps.setInt(2, end);
        ResultSet set = ps.executeQuery();
        List<Account> accounts = new ArrayList<Account>();
        while (set.next()) {
          Account account = new Account();
          account.setEmail(set.getString("email"));
          account.setAddress(set.getString("address"));
          account.setUsername(set.getString("username"));
          account.setPassword(set.getString("password"));
          account.setLastName(set.getString("lastname"));
          account.setFirstName(set.getString("firstname"));
          account.setAdmin(set.getBoolean("admin"));
          accounts.add(account);
        }
        set.close();
        return accounts;
      }
    }
  }

  @Override
  public Optional<Account> fetchAccount(String email) throws SQLException {
    return Optional.empty();
  }

  @Override
  public Integer createAccount(Account account) throws SQLException {
    try (Connection conn = source.getConnection()) {
      try (PreparedStatement ps = conn.prepareStatement(QUERY.insertAccounts())) {
        ps.setString(1, account.getAddress());
        ps.setString(2, account.getUsername());
        ps.setString(3, account.getPassword());
        ps.setString(4, account.getFirstName());
        ps.setString(5, account.getLastName());
        ps.setString(6, account.getEmail());
        ps.setBoolean(7, account.getAdmin());
        return ps.executeUpdate();
      }
    }
  }

  @Override
  public Integer updateAccount(Account account) throws SQLException {
    return null;
  }

  @Override
  public Integer deleteAccount(String email) throws SQLException {
    return null;
  }
}
