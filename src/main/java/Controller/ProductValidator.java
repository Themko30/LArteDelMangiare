package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class ProductValidator {

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch("fullname", Pattern.compile("^\\w{5,30}$"), "Name Between 5 and 30 Cha");
    validator.assertInt("quantity", "Quantity Must Be An Integer");
    validator.assertDouble("price", "Price Must Be a Double");
    validator.assertInt("couId", "Country Id Must Be An Integer");
    validator.assertInt("catId", "Category Must Be An Integer");
    validator.assertMatch(
        "label", Pattern.compile("^\\w{5,100}$"), "Decription Between 5 and 100 Cha");
    return validator;
  }
}
