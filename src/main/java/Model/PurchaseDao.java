package Model;

import java.util.List;
import java.util.Optional;

public interface PurchaseDao<E extends Exception> {

  List<Purchase> fetchPurchases(int start, int end) throws E;

  List<Purchase> fetchPurchasesWithProducts(int accountId) throws E;

  Optional<Purchase> fetchPurchase(int id) throws E;

  boolean createPurchase(Purchase purchase) throws E;
}
