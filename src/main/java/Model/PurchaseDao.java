package Model;

import java.util.List;
import java.util.Optional;

public interface PurchaseDao<E extends Exception> {

  List<Purchase> fetchPurchases(Paginator paginator) throws E;

  List<Purchase> fetchPurchasesWithProducts(int accountId, Paginator paginator) throws E;

  Optional<Purchase> fetchPurchase(int id) throws E;

  int countAll() throws E;

  int sum() throws E;

  boolean createPurchase(Purchase purchase) throws E;
}
