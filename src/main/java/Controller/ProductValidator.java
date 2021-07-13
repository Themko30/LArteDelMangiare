package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class ProductValidator {

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "fullName",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Name Must Be In A Valid Format");
    validator.assertInt("quantity", "Quantity Must Be An Integer");
    validator.assertDouble("price", "Price Must Be a Double");
    validator.assertInt("couId", "Country Id Must Be An Integer");
    validator.assertInt("catId", "Category Must Be An Integer");
    validator.assertMatch(
        "description",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Description Must Be In A Valid Format");
    return validator;
  }
}
