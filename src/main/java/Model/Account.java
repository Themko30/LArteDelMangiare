package Model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
      MessageDigest digest = MessageDigest.getInstance("SHA-512");
      SecureRandom ss = new SecureRandom();
      byte[] salt = new byte[16];
      ss.nextBytes(salt);
      digest.update(salt);
      byte[] hashedPwd = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      StringBuilder builder = new StringBuilder();
      for (byte bit : hashedPwd) {
        builder.append(String.format("%02x", bit));
      }
      this.password = builder.toString();
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
