package Model;

import java.time.LocalDate;

public class Purchase {

  private int code, panCard;
  private String cardCircuit;
  private LocalDate created;
  private Account account;
  private Cart card;

  public Purchase() {
    super();
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public int getPanCard() {
    return panCard;
  }

  public void setPanCard(int panCard) {
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

  public Cart getCard() {
    return card;
  }

  public void setCard(Cart card) {
    this.card = card;
  }

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }
}
