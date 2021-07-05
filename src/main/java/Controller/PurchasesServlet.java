package Controller;

import Model.Alert;
import Model.Paginator;
import Model.Purchase;
import Model.PurchaseDao;
import Model.SqlPurchaseDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PurchasesServlet", value = "/purchases/*")
public class PurchasesServlet extends Controller implements ErrorHandler {

  private PurchaseDao<SQLException> purchaseDao;

  @Override
  public void init() throws ServletException {
    super.init();
    purchaseDao = new SqlPurchaseDAO(source);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = getPath(request);
      switch (path) {
        case "/":
          /*authorize(request.getSession(false));*/
          int intPage = parsePage(request);
          Paginator paginator = new Paginator(intPage, 30);
          int size = 0;
          size = purchaseDao.countAll();
          request.setAttribute("pages", paginator.getPages(size));
          List<Purchase> purchases = null;
          purchases = purchaseDao.fetchPurchases(paginator);
          request.setAttribute("purchases", purchases);
          request.getRequestDispatcher(view("crm/purchases")).forward(request, response);
          break;
        case "/show":
          /*authorize(request.getSession(false));*/
          validate(CommonValidator.validateId(request));
          int id = Integer.parseInt(request.getParameter("id"));
          Optional<Purchase> optionalPurchase = purchaseDao.fetchPurchase(id);
          if (optionalPurchase.isPresent()) {
            request.setAttribute("purchase", optionalPurchase.get());
            request.getRequestDispatcher(view("crm/purchase")).forward(request, response);
          } else {
            notFound();
          }
          break;
        case "/create":
          /*authorize(request.getSession(false));*/
          request.getRequestDispatcher(view("crm/purchase")).forward(request, response);
          break;
        case "/profile":
          HttpSession accSession = request.getSession(false);
          authenticate(accSession);
          int accId = getAccountSession(accSession).getId();
          List<Purchase> accountPurchases = purchaseDao.fetchPurchasesWithProducts(accId);
          request.setAttribute("purchases", accountPurchases);
          request.getRequestDispatcher(view("site/profile")).forward(request, response);
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

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = getPath(request);
      switch (path) {
        case "/create":
          /*authorize(request.getSession(false));*/
          request.setAttribute("back", view("crm/purchase"));
          validate(PurchaseValidator.validateForm(request));
          Purchase purchase = new Purchase();
          purchase.setCardCircuit(request.getParameter("cardCircuit"));
          purchase.setPanCard(Long.parseLong(request.getParameter("cardNumber")));
          purchase.setCreated(LocalDate.parse(request.getParameter("date")));
          purchase.setTotal(Double.parseDouble(request.getParameter("total")));
          purchase.setAccountNum(Integer.parseInt(request.getParameter("accountId")));
          if (purchaseDao.createPurchaseAdmin(purchase)) {
            request.setAttribute("alert", new Alert(List.of("Purchase Created!"), "success"));
            response.setStatus(HttpServletResponse.SC_CREATED);
            request.getRequestDispatcher(view("crm/purchase")).forward(request, response);
          } else {
            internalError();
          }
          break;
        case "/update":
          /*authorize(request.getSession(false));*/
          request.setAttribute("back", view("crm/purchase"));
          validate(PurchaseValidator.validateForm(request));
          Purchase purchaseup = new Purchase();
          purchaseup.setId(Integer.parseInt(request.getParameter("id")));
          purchaseup.setCardCircuit(request.getParameter("cardCircuit"));
          purchaseup.setPanCard(Long.parseLong(request.getParameter("cardNumber")));
          purchaseup.setCreated(LocalDate.parse(request.getParameter("date")));
          purchaseup.setTotal(Double.parseDouble(request.getParameter("total")));
          purchaseup.setAccountNum(Integer.parseInt(request.getParameter("accountId")));
          request.setAttribute("purchase", purchaseup);
          if (purchaseDao.updatePurchaseAdmin(purchaseup)) {
            request.setAttribute("alert", new Alert(List.of("Purchase Updated!"), "success"));
            request.getRequestDispatcher(view("crm/purchase")).forward(request, response);
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
