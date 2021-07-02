package Model;

import java.util.List;

final class ProductQuery {

  private static final String PRODUCT_TABLE = "products";
  private static final String PRODUCT_ALIAS = "pro";

  static String fetchProductsWithRelations() {
    QueryBuilder builder = new QueryBuilder(PRODUCT_TABLE, PRODUCT_ALIAS);
    builder.select().outerJoin(true, "category", "cat").on("pro.category_fk = cat.id");
    builder.outerJoin(true, "country", "cou").on("pro.country_fk = cou.id");
    builder.limit(true);
    return builder.generateQuery();
  }

  static String fetchProductWithRelations() {
    QueryBuilder builder = new QueryBuilder(PRODUCT_TABLE, PRODUCT_ALIAS);
    builder.select().outerJoin(true, "category", "cat").on("pro.category_fk = cat.id");
    builder.outerJoin(true, "country", "cou").on("pro.country_fk = cou.id");
    builder.where("pro.id=?");
    return builder.generateQuery();
  }

  static String search(List<Condition> conditionList) {
    QueryBuilder builder = new QueryBuilder(PRODUCT_TABLE, PRODUCT_ALIAS);
    builder.select().innerJoin("category", "cat").on("pro.category_fk = cat.id");
    builder.innerJoin("country", "cou").on("pro.country_fk = cou.id");
    if (conditionList.isEmpty()) {
      builder.where().search(conditionList);
    }
    return builder.generateQuery();
  }
}
