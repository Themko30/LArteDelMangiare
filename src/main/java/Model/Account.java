package Model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Account {

  private int id;
  private String address, username, password, firstName, lastName, email;
  private Boolean admin;
  private List<Purchase> purchases;

  public Account() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-1");
      digest.reset();
      digest.update(password.getBytes(StandardCharsets.UTF_8));
      this.password = String.format("%040x", new BigInteger(1, digest.digest()));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getAdmin() {
    return admin;
  }

  public void setAdmin(Boolean admin) {
    this.admin = admin;
  }

  public List<Purchase> getPurchases() {
    return purchases;
  }

  public void setPurchases(List<Purchase> purchases) {
    this.purchases = purchases;
  }
}
