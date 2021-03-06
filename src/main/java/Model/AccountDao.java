package Model;

import java.util.List;
import java.util.Optional;

public interface AccountDao<E extends Exception> {

  List<Account> fetchAccounts(Paginator paginator) throws E;

  Optional<Account> fetchAccount(int id) throws E;

  Optional<Account> findAccount(String email, String password, boolean admin) throws E;

  int countAll() throws E;

  boolean createAccount(Account account) throws E;

  boolean updateAccount(Account account) throws E;

  boolean deleteAccount(int id) throws E;
}
