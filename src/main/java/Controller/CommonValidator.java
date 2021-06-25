package Controller;

import javax.servlet.http.HttpServletRequest;

public class CommonValidator {

  public static RequestValidator validatePage(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertInt("page", "Page Number Must Be In A Valid Format");
    return validator;
  }

  public static RequestValidator validateId(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertInt("id", "Id Must Be In A Valid Format");
    return validator;
  }
}
