package Controller;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

final class AccountValidator {

  static RequestValidator validateSignin(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertEmail("email", "Wrong Email or Password");
    validator.assertMatch(
        "password",
        Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$"),
        "Wrong Email or Password");
    return validator;
  }

  static RequestValidator validateForm(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "userName",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Username Between 5 and 30 Cha");
    validator.assertMatch(
        "firstName",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Name Must Be In A Valid Format");
    validator.assertMatch(
        "lastName",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Surname Must Be In A Valid Format");
    validator.assertMatch(
        "address",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Address Must Be In A Valid Format");
    validator.assertMatch(
        "email", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Email Must Be In A Valid Format");
    validator.assertMatch(
        "password",
        Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$"),
        "Password Must contain at least one number and one uppercase and lowercase letter, and at least 8 and max 16 characters");
    return validator;
  }

  static RequestValidator validateFormUpdate(HttpServletRequest request) {
    RequestValidator validator = new RequestValidator(request);
    validator.assertMatch(
        "userName",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Username Between 5 and 30 Cha");
    validator.assertMatch(
        "firstName",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Name Must Be In A Valid Format");
    validator.assertMatch(
        "lastName",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Surname Must Be In A Valid Format");
    validator.assertMatch(
        "address",
        Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"),
        "Address Must Be In A Valid Format");
    validator.assertMatch(
        "email", Pattern.compile("^(.|\\s)*[a-zA-Z]+(.|\\s)*$"), "Email Must Be In A Valid Format");
    return validator;
  }
}
