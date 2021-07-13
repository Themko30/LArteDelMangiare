package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class CountryValidator {

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "label", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Name Must Be In A Valid Format");
    return validator;
  }
}
