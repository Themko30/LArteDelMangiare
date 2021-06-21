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
          request
              .getRequestDispatcher("/WEB-INF/views/crm/accounts.jsp")
              .forward(request, response);
          break;
        }
      case "/create":
        {
          request.getRequestDispatcher("/WEB-INF/views/crm/account.jsp").forward(request, response);
          break;
        }
      case "/show":
        {
          request
              .getRequestDispatcher("/WEB-INF/views/crm/accounts.jsp")
              .forward(request, response);
          break;
        }
      case "/secret":
        {
          request.getRequestDispatcher("/WEB-INF/views/crm/secret.jsp").forward(request, response);
          break;
        }
      case "/ signinClient":
        {
          request.getRequestDispatcher("/WEB-INF/views/crm/signin.jsp").forward(request, response);
          break;
        }
      case "/signupClient":
        {
          request.getRequestDispatcher("/WEB-INF/views/crm/signup.jsp").forward(request, response);
          break;
        }
      case "/profile":
        {
          request.getRequestDispatcher("/WEB-INF/views/crm/profile.jsp").forward(request, response);
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
