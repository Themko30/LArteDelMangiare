package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AccountServlet", value = "/accounts/*")
public class AccountServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
    switch (path) {
      case "/":
        {
          break;
        }
      case "/create":
        {
          break;
        }
      case "/show":
        {
          break;
        }
      case "/secret":
        {
          break;
        }
      case "/ signinClient":
        {
          break;
        }
      case "/signupClient":
        {
          break;
        }
      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
    switch (path) {
      case "/secret":
        {
          break;
        }
      case "/create":
        {
          break;
        }
      case "/update":
        {
          break;
        }
      case "/logout":
        {
          break;
        }
      case "/signup":
        {
          break;
        }
      case "/signin":
        {
          break;
        }
      default:
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Operation not allowed");
    }
  }
}
