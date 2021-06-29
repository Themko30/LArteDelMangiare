package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class CountryValidator {

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "label", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Name Between 5 and 30 Cha");
    return validator;
  }
}
