package Model;

public class AccountSession {

  private final String firstName, lastName, username;
  private final int id;
  private final boolean isAdmin;

  public AccountSession(Account account) {
    this.firstName = account.getFirstName();
    this.lastName = account.getLastName();
    this.id = account.getId();
    this.isAdmin = account.getAdmin();
    this.username = account.getUsername();
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

  public String getUsername() {
    return username;
  }
}
