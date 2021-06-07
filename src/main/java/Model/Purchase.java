package Model;

import java.time.LocalDate;

public class Purchase {

  private int id;
  private double total;
  private Long panCard;
  private String cardCircuit;
  private LocalDate created;
  private Account account;
  private Cart cart;

  public Purchase() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Long getPanCard() {
    return panCard;
  }

  public void setPanCard(Long panCard) {
    this.panCard = panCard;
  }

  public String getCardCircuit() {
    return cardCircuit;
  }

  public void setCardCircuit(String cardCircuit) {
    this.cardCircuit = cardCircuit;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }

  public int entries() {}
}
