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

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "userName",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Username Between 5 and 30 Cha");
    validator.assertMatch(
        "firstName", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Name Between 5 and 30 Cha");
    validator.assertMatch(
        "lastName", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Surname Between 5 and 30 Cha");
    validator.assertMatch(
        "address", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Address Between 5 and 30 Cha");
    validator.assertMatch(
        "email", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Email Between 5 and 30 Cha");
    validator.assertMatch(
        "password",
        Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$"),
        "Password Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters");
    return validator;
  }
}
