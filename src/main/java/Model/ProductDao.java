package Model;

import java.util.List;
import java.util.Optional;

public interface ProductDao<E extends Exception> {

  List<Product> fetchProducts(Paginator paginator) throws E;

  Optional<Product> fetchProduct(int id) throws E;

  List<Product> fetchProductsByCat(int catId, Paginator paginator) throws E;

  List<Product> fetchProductsWithRelations(Paginator paginator) throws E;

  public Optional<Product> fetchProductWithRelations(int id) throws E;

  int countAll() throws E;

  int countAllByCat(int catId) throws E;

  int sum() throws E;

  Optional<Product> fetchProductByLabel(String label) throws E;

  boolean createProduct(Product product) throws E;

  boolean updateProduct(Product product) throws E;

  boolean deleteProduct(int id) throws E;

  List<Product> search(List<Condition> conditions) throws E;
}
