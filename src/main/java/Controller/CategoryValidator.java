package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class CategoryValidator {

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "label", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Name Must Be In A Valid Format");
    validator.assertMatch(
        "description",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Description Must Be In A Valid Format");
    return validator;
  }
}
