package Controller;

import Model.AccountDao;
import Model.ProductDao;
import Model.PurchaseDao;
import Model.SqlAccountDAO;
import Model.SqlProductDAO;
import Model.SqlPurchaseDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CrmServlet", value = "/crm/*")
public class CrmServlet extends Controller {

  private AccountDao<SQLException> accountDao;
  private ProductDao<SQLException> productDao;
  private PurchaseDao<SQLException> purchaseDao;

  @Override
  public void init() throws ServletException {
    super.init();
    accountDao = new SqlAccountDAO(source);
    productDao = new SqlProductDAO(source);
    purchaseDao = new SqlPurchaseDAO(source);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String path = getPath(request);
    try {
      switch (path) {
        case "/dashboard":
          int sizeAccounts = 0;
          sizeAccounts = accountDao.countAll();
          request.setAttribute("sizeAccounts", sizeAccounts);
          int sizeProducts = 0;
          sizeProducts = productDao.sum();
          request.setAttribute("sizeProducts", sizeProducts);
          int earnings = 0;
          earnings = purchaseDao.sum();
          request.setAttribute("earnings", earnings);
          int purchases = 0;
          purchases = purchaseDao.countAll();
          request.setAttribute("purchases", purchases);
          request.getRequestDispatcher(view("crm/home")).forward(request, response);
          break;
        default:
          response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource Not Found");
      }
    } catch (SQLException ex) {
      log(ex.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    }
  }
}
