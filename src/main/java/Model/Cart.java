package Model;

import java.util.List;
import java.util.Optional;

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

  public boolean addProduct(Product product, int quantity) {
    Optional<CartItem> optionalCartItem = find(product.getId());
    if (optionalCartItem.isPresent()) {
      optionalCartItem.get().setQuantity(quantity);
      return true;
    } else {
      return items.add(new CartItem(product, quantity));
    }
  }

  public Optional<CartItem> find(int id) {
    return items.stream().filter(it -> it.getProduct().getId() == id).findFirst();
  }

  public boolean removeProducts(int id) {
    return items.removeIf(it -> it.getProduct().getId() == id);
  }

  public int quantity() {
    return items.stream().mapToInt(CartItem::getQuantity).reduce(0, Integer::sum);
  }

  public double total() {
    return items.stream().mapToDouble(ct -> ct.total()).reduce(0.0, Double::sum);
  }

  public void resetCart() {
    items.clear();
  }
}
