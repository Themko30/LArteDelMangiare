package Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountDao {

  List<Account> fetchAccounts(int start, int end) throws SQLException;

  Optional<Account> fetchAccount(String email) throws SQLException;

  Integer createAccount(Account account) throws SQLException;

  Integer updateAccount(Account account) throws SQLException;

  Integer deleteAccount(String email) throws SQLException;
}
