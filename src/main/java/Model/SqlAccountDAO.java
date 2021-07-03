package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlAccountDAO extends SqlDao implements AccountDao<SQLException> {

  public SqlAccountDAO(DataSource source) {
    super(source);
  }

  @Override
  public List<Account> fetchAccounts(Paginator paginator) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("account", "acc");
      String query = queryBuilder.select().limit(true).generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, paginator.getOffset());
        ps.setInt(2, paginator.getLimit());
        ResultSet set = ps.executeQuery();
        AccountExtractor accountExtractor = new AccountExtractor();
        List<Account> accounts = new ArrayList<>();
        while (set.next()) {
          accounts.add(accountExtractor.extract(set));
        }
        return accounts;
      }
    }
  }

  @Override
  public Optional<Account> fetchAccount(int id) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("account", "acc");
      String query = queryBuilder.select().where("acc.id=?").generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        Account account = null;
        if (set.next()) {
          account = new AccountExtractor().extract(set);
        }
        return Optional.ofNullable(account);
      }
    }
  }

  @Override
  public Optional<Account> findAccount(String email, String password, boolean admin)
      throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("account", "acc");
      String query =
          queryBuilder
              .select()
              .where("acc.email=?")
              .and("acc.password=?")
              .and("acc.admin=?")
              .generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, email);
        ps.setString(2, password);
        ps.setBoolean(3, admin);
        ResultSet set = ps.executeQuery();
        Account account = null;
        if (set.next()) {
          account = new AccountExtractor().extract(set);
        }
        return Optional.ofNullable(account);
      }
    }
  }

  @Override
  public int countAll() throws SQLException {
    try (Connection conn = source.getConnection()) {
      String query = ("SELECT COUNT(*) FROM account AS acc ");
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
  public boolean createAccount(Account account) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("account", "acc");
      queryBuilder.insert(
          "id", "address", "username", "password", "firstname", "lastname", "email", "admin");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, account.getId());
        ps.setString(2, account.getAddress());
        ps.setString(3, account.getUsername());
        ps.setString(4, account.getPassword());
        ps.setString(5, account.getFirstName());
        ps.setString(6, account.getLastName());
        ps.setString(7, account.getEmail());
        ps.setBoolean(8, account.getAdmin());
        int rows = ps.executeUpdate();
        return rows == 1;
      }
    }
  }

  @Override
  public boolean updateAccount(Account account) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("account", "acc");
      queryBuilder.update("address", "username", "lastname", "firstname", "email").where("id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setString(1, account.getAddress());
        ps.setString(2, account.getUsername());
        ps.setString(3, account.getFirstName());
        ps.setString(4, account.getLastName());
        ps.setString(5, account.getEmail());
        ps.setInt(6, account.getId());
        int rows = ps.executeUpdate();
        return rows == 1;
      }
    }
  }

  @Override
  public boolean deleteAccount(int id) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("account", "acc");
      queryBuilder.delete().where("id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        return rows == 1;
      }
    }
  }
}
