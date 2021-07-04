package Controller;

import Model.Account;
import Model.AccountDao;
import Model.AccountSession;
import Model.Alert;
import Model.Paginator;
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
          /*authorize(request.getSession(false));*/
          int intPage = parsePage(request);
          Paginator paginator = new Paginator(intPage, 30);
          int size = 0;
          size = accountDao.countAll();
          request.setAttribute("pages", paginator.getPages(size));
          List<Account> accounts = null;
          accounts = accountDao.fetchAccounts(paginator);
          request.setAttribute("accounts", accounts);
          request.getRequestDispatcher(view("crm/accounts")).forward(request, response);
          break;
        case "/create":
          request.getRequestDispatcher(view("crm/account")).forward(request, response);
          break;
        case "/show":
          /*authorize(request.getSession(false));*/
          validate(CommonValidator.validateId(request));
          int id = Integer.parseInt(request.getParameter("id"));
          Optional<Account> optionalAccount = accountDao.fetchAccount(id);
          if (optionalAccount.isPresent()) {
            request.setAttribute("account", optionalAccount.get());
            request.getRequestDispatcher(view("crm/account")).forward(request, response);
          } else {
            notFound();
          }
          break;
        case "/secret":
          request.getRequestDispatcher(view("crm/secret")).forward(request, response);
          break;
        case "/signin":
          request.getRequestDispatcher(view("account/signinPage")).forward(request, response);
          break;
        case "/signup":
          request.getRequestDispatcher(view("account/signupPage")).forward(request, response);
          break;
        case "/profile":
          request.getRequestDispatcher(view("crm/profile")).forward(request, response);
          break;
        case "/logout":
          HttpSession session = request.getSession(false);
          authenticate(session);
          AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
          String redirect =
              accountSession.isAdmin()
                  ? "/LArteDelMangiare_war_exploded/accounts/secret"
                  : "/LArteDelMangiare_war_exploded/pages/home?page=1";
          session.removeAttribute("accountSession");
          session.invalidate();
          response.sendRedirect(redirect);
          break;
        default:
          notFound();
      }
    } catch (SQLException ex) {
      log(ex.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
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
          request.setAttribute("back", view("account/signinPage"));
          /*validate(AccountValidator.validateSignin(request));*/
          Account tmpAccount = new Account();
          tmpAccount.setEmail(request.getParameter("email"));
          tmpAccount.setPassword(request.getParameter("password"));
          Optional<Account> optionalAccount =
              accountDao.findAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), true);
          if (optionalAccount.isPresent()) {
            AccountSession accountSession = new AccountSession(optionalAccount.get());
            request.getSession(true).setAttribute("accountSession", accountSession);
            response.sendRedirect("/LArteDelMangiare_war_exploded/crm/dashboard");
          } else {
            notAllowed();
          }
          break;
        case "/signin":
          request.setAttribute("back", view("account/signinPage"));
          validate(AccountValidator.validateSignin(request));
          Account tmpAccount2 = new Account();
          tmpAccount2.setEmail(request.getParameter("email"));
          tmpAccount2.setPassword(request.getParameter("password"));
          Optional<Account> optionalAccount2 =
              accountDao.findAccount(tmpAccount2.getEmail(), tmpAccount2.getPassword(), false);
          if (optionalAccount2.isPresent()) {
            AccountSession accountSession = new AccountSession(optionalAccount2.get());
            request.getSession(true).setAttribute("accountSession", accountSession);
            response.sendRedirect("/LArteDelMangiare_war_exploded/pages/home?page=1");
          } else {
            request.setAttribute("alert", new Alert(List.of("Account Not Found!"), "danger"));
            request.getRequestDispatcher(view("account/signinPage")).forward(request, response);
          }
          break;
        case "/signup":
          request.setAttribute("back", view("account/signupPage"));
          validate(AccountValidator.validateForm(request));
          Account accountUser = new Account();
          accountUser.setUsername(request.getParameter("userName"));
          accountUser.setFirstName(request.getParameter("firstName"));
          accountUser.setLastName(request.getParameter("lastName"));
          accountUser.setAddress(request.getParameter("address"));
          accountUser.setEmail(request.getParameter("email"));
          accountUser.setPassword(request.getParameter("password"));
          accountUser.setAdmin(false);
          if (accountDao.createAccount(accountUser)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            request.setAttribute(
                "alert", new Alert(List.of("Account Created! Please log"), "success"));
            request.getRequestDispatcher(view("account/signinPage")).forward(request, response);
          } else {
            internalError();
          }
          break;
        case "/create":
          /*authorize(request.getSession(false));*/
          request.setAttribute("back", view("crm/account"));
          validate(AccountValidator.validateForm(request));
          Account account = new Account();
          account.setUsername(request.getParameter("userName"));
          account.setFirstName(request.getParameter("firstName"));
          account.setLastName(request.getParameter("lastName"));
          account.setAddress(request.getParameter("address"));
          account.setEmail(request.getParameter("email"));
          account.setPassword(request.getParameter("password"));
          account.setAdmin(Boolean.valueOf(request.getParameter("admin")));
          if (accountDao.createAccount(account)) {
            request.setAttribute("alert", new Alert(List.of("Account Created!"), "success"));
            response.setStatus(HttpServletResponse.SC_CREATED);
            request.getRequestDispatcher(view("crm/account")).forward(request, response);
          } else {
            internalError();
          }
          break;
        case "/update":
          /*authorize(request.getSession(false));*/
          request.setAttribute("back", view("crm/account"));
          validate(AccountValidator.validateForm(request));
          Account accountup = new Account();
          accountup.setId(Integer.parseInt(request.getParameter("id")));
          accountup.setUsername(request.getParameter("userName"));
          accountup.setFirstName(request.getParameter("firstName"));
          accountup.setLastName(request.getParameter("lastName"));
          accountup.setAddress(request.getParameter("address"));
          accountup.setEmail(request.getParameter("email"));
          accountup.setAdmin(Boolean.parseBoolean(request.getParameter("admin")));
          request.setAttribute("account", accountup);
          if (accountDao.updateAccount(accountup)) {
            request.setAttribute("alert", new Alert(List.of("Account Updated!"), "success"));
            request.getRequestDispatcher(view("crm/account")).forward(request, response);
          } else {
            internalError();
          }
          break;
      }
    } catch (SQLException ex) {
      log(ex.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    } catch (InvalidRequestException e) {
      log(e.getMessage());
      e.handle(request, response);
    }
  }
}
