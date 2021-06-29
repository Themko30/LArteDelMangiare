package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseExtractor implements ResultSetExtractor<Purchase> {

  @Override
  public Purchase extract(ResultSet resultSet) throws SQLException {
    Purchase purchase = new Purchase();
    purchase.setId(resultSet.getInt("pur.id"));
    purchase.setCardCircuit(resultSet.getString("pur.card_circuit"));
    purchase.setPanCard(resultSet.getLong("pur.pan_card"));
    purchase.setCreated(resultSet.getDate("pur.date").toLocalDate());
    purchase.setTotal(resultSet.getDouble("pur.total"));
    purchase.setAccountNum(resultSet.getInt("pur.account_fk"));
    return purchase;
  }
}
