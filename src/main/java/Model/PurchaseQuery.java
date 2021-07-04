package Model;

final class PurchaseQuery {

  private static final String PURCHASE_QUERY = "purchase";
  private static final String PURCHASE_ALIAS = "pur";

  static String fetchPurchases() {
    QueryBuilder builder = new QueryBuilder(PURCHASE_QUERY, PURCHASE_ALIAS);
    builder.select().limit(true);
    return builder.generateQuery();
  }

  static String fetchPurchasesWithProducts() {
    QueryBuilder builder = new QueryBuilder("purchase_products", "pp");
    builder.select().innerJoin(PURCHASE_QUERY, PURCHASE_ALIAS).on("pp.purchase_fk = pur.id");
    builder.innerJoin("products", "pro").on("pp.product_fk = pro.id");
    builder.where("pur.account_fk=?");
    return builder.generateQuery();
  }

  static String fetchPurchase() {
    QueryBuilder builder = new QueryBuilder(PURCHASE_QUERY, PURCHASE_ALIAS);
    builder.select().where("pur.id=?");
    return builder.generateQuery();
  }

  static String createPurchase() {
    QueryBuilder builder = new QueryBuilder(PURCHASE_QUERY, PURCHASE_ALIAS);
    builder.insert("card_circuit", "pan_card", "date", "total", "account_fk");
    return builder.generateQuery();
  }

  static String insertCart() {
    QueryBuilder builder = new QueryBuilder("purchase_products", "pp");
    builder.insert("product_fk", "purchase_fk", "quantity");
    return builder.generateQuery();
  }
}
