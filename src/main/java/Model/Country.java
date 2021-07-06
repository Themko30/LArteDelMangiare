package Model;

import java.util.List;
import org.json.JSONObject;

public class Country implements JsonSerializable {

  private int id;
  private String label;
  private List<Product> products;

  public Country() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public int countProducts() {
    return products.size();
  }

  public boolean hasProducts() {
    return products.isEmpty();
  }

  @Override
  public JSONObject toJson() {
    JSONObject obj = new JSONObject();
    obj.put("id", id);
    obj.put("label", label);
    return obj;
  }
}
