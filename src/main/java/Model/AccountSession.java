package Model;

public class AccountSession {

  private final String firstName, lastName;
  private final int id;
  private final boolean isAdmin;

  public AccountSession(Account account) {
    this.firstName = account.getFirstName();
    this.lastName = account.getLastName();
    this.id = account.getId();
    this.isAdmin = account.getAdmin();
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getId() {
    return id;
  }

  public boolean isAdmin() {
    return isAdmin;
  }
}
