package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class CategoryValidator {

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "label", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Name Between 5 and 30 Cha");
    validator.assertMatch(
        "description",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Description Between 5 and 100 Cha");
    return validator;
  }
}
