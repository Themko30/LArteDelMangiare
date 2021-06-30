package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class PurchaseValidator {

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "cardCircuit", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Card Circuit Error");
    validator.assertInt("cardNumber", "Card Pan Error");
    validator.assertMatch(
        "date",
        Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$"),
        "Date Error");
    validator.assertDouble("total", "Total Error");
    validator.assertInt("accountId", "Account Id Error");

    return validator;
  }
}
