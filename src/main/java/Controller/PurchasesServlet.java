package Controller;

import Model.Paginator;
import Model.Purchase;
import Model.PurchaseDao;
import Model.SqlPurchaseDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
      throws ServletException, IOException {}
}
