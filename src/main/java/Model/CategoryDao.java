package Model;

import java.util.List;
import java.util.Optional;

public interface CategoryDao<E extends Exception> {

  List<Category> fetchCategories() throws E;

  Optional<Category> fetchCategory(int id) throws E;

  boolean createCategories(Category category) throws E;

  boolean updateCategories(Category category) throws E;

  Optional<Category> fetchCategoriesWithProducts(int categoryId) throws E;
}
