package Controller;

import Model.Account;
import Model.AccountDao;
import Model.AccountSession;
import Model.SqlAccountDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AccountServlet", value = "/accounts/*")
public class AccountServlet extends Controller implements ErrorHandler {

  private AccountDao<SQLException> accountDao;

  @Override
  public void init() throws ServletException {
    super.init();
    accountDao = new SqlAccountDAO(source);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
      switch (path) {
        case "/":
          request
              .getRequestDispatcher("/WEB-INF/views/crm/accounts.jsp")
              .forward(request, response);
          break;

        case "/create":
          request.getRequestDispatcher("/WEB-INF/views/crm/account.jsp").forward(request, response);
          break;

        case "/show":
          request
              .getRequestDispatcher("/WEB-INF/views/crm/accounts.jsp")
              .forward(request, response);
          break;

        case "/secret":
          request.getRequestDispatcher("/WEB-INF/views/crm/secret.jsp").forward(request, response);
          break;

        case "/signin":
          request.getRequestDispatcher("/WEB-INF/views/crm/signin.jsp").forward(request, response);
          break;

        case "/signup":
          request.getRequestDispatcher("/WEB-INF/views/crm/signup.jsp").forward(request, response);
          break;

        case "/profile":
          request.getRequestDispatcher("/WEB-INF/views/crm/profile.jsp").forward(request, response);
          break;

        case "/logout":
          HttpSession session = request.getSession(false);
          authenticate(session);
          AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
          String redirect =
              accountSession.isAdmin()
                  ? "/LArteDelMangiare_war_exploded/accounts/secret"
                  : "/LArteDelMangiare_war_exploded/accounts/signin";
          session.removeAttribute("accountSession");
          session.invalidate();
          response.sendRedirect(redirect);
          break;

        default:
          notFound();
      }
    } catch (InvalidRequestException e) {
      log(e.getMessage());
      e.handle(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = getPath(request);
      switch (path) {
        case "/secret":
          request.setAttribute("back", view("crm/secret"));
          validate(AccountValidator.validateSignin(request));
          Account tmpAccount = new Account();
          tmpAccount.setEmail(request.getParameter("email"));
          tmpAccount.setPassword(request.getParameter("password"));
          Optional<Account> optionalAccount =
              accountDao.findAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), true);
          if (optionalAccount.isPresent()) {
            AccountSession accountSession = new AccountSession(optionalAccount.get());
            request.getSession(true).setAttribute("accountSession", accountSession);
            response.sendRedirect("/LArteDelMangiare_war_exploded/pages/dashboard");
          } else {
            throw new InvalidRequestException(
                "Not Valid Credentials",
                List.of("Not Valid Credentials"),
                HttpServletResponse.SC_BAD_REQUEST);
          }
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } catch (InvalidRequestException e) {
      e.printStackTrace();
    }
  }
}
