package Model;

import java.util.List;

public class Cart {

  private List<CartItem> items;

  public Cart(List<CartItem> items) {
    this.items = items;
  }

  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }

  public boolean addProducts(Product product, int quantity) {
    return items.add(new CartItem(product, quantity));
  }

  public double total() {
    double total = 0;
    for (CartItem item : items) {
      total += item.total();
    }
    return total;
    // return items.stream().mapToDouble(ct -> ct.total()).reduce(0.0, Double::sum));
  }
}
