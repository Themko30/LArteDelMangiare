package Model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import javax.servlet.http.Part;

public class Category {

  private int id;
  private String label, description, image;
  private List<Product> products;

  public Category() {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public void writeCover(String uploadPath, Part stream) throws IOException {
    try (InputStream fileStream = stream.getInputStream()) {
      File file = new File(uploadPath + image);
      Files.copy(fileStream, file.toPath());
    }
  }
}
