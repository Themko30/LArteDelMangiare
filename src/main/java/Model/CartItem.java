package Model;

public class CartItem {

  private final Product product;
  private int quantity;

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public CartItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public double total() {
    return product.getPrice() * quantity;
  }
}
