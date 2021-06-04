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

          accounts.add(account);
        }
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
    return null;
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
