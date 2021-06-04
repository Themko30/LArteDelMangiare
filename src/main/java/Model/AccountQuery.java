package Model;

public class AccountQuery extends TableQuery {

  AccountQuery(String table) {
    super(table);
  }

  String selectAccounts() {
    return String.format("SELECT * FROM %s LIMIT ?,?;", this.table);
  }

  String selectAccount() {
    return String.format("SELECT * FROM %s WHERE email=?;", table);
  }

  String insertAccounts() {
    return String.format(
        "INSERT INTO %s (address, username, password, firstName, lastName, email, admin) VALUES (?,?,?,?,?,?,?);",
        table);
  }

  String updateAccounts() {
    return String.format(
        "UPDATE FROM %s SET firstname=?, lastname=?, username=?, address=? WHERE email=?", table);
  }

  String deleteAccount() {
    return String.format("DELETE FROM %s WHERE email=?", table);
  }
}
