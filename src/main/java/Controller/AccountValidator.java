package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

final class AccountValidator {

  static RequestValidator validateSignin(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertEmail("email", "Check email");
    validator.assertMatch(
        "password", Pattern.compile("^\\w{5,30}$"), "Password Must Be Between 5 an 30 Charachters");
    return validator;
  }
}
