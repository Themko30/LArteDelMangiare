package Controller;

import Model.Cart;
import Model.Product;
import Model.ProductDao;
import Model.Purchase;
import Model.PurchaseDao;
import Model.SqlProductDAO;
import Model.SqlPurchaseDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartServlet", value = "/carts/*")
public class CartServlet extends Controller implements ErrorHandler {

  private ProductDao<SQLException> productDao;
  private PurchaseDao<SQLException> purchaseDao;

  @Override
  public void init() throws ServletException {
    super.init();
    productDao = new SqlProductDAO(source);
    purchaseDao = new SqlPurchaseDAO(source);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = getPath(request);
      switch (path) {
        case "/show":
          request.getRequestDispatcher(view("site/cart")).forward(request, response);
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
        case "/add":
          request.setAttribute("back", view("pages/search"));
          int id = Integer.parseInt(request.getParameter("id"));
          Optional<Product> optionalProduct = productDao.fetchProductWithRelations(id);
          if (optionalProduct.isPresent()) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (request.getSession(false).getAttribute("accountCart") == null) {
              request.getSession(false).setAttribute("accountCart", new Cart(new ArrayList<>()));
            }
            getSessionCart(request.getSession(false)).addProduct(optionalProduct.get(), quantity);
            response.sendRedirect(
                "/LArteDelMangiare_war_exploded/products/details?id="
                    + optionalProduct.get().getId());
          } else {
            notFound();
          }
          break;
        case "/remove":
          validate(CommonValidator.validateId(request));
          int removeId = Integer.parseInt(request.getParameter("id"));
          if (getSessionCart(request.getSession(false)).removeProducts(removeId)) {
            response.sendRedirect("/LArteDelMangiare_war_exploded/carts/show");
          } else {
            notFound();
          }
          break;
        case "/createClient":
          HttpSession session = request.getSession(false);
          authenticate(session);
          Purchase customerPurchase = new Purchase();
          customerPurchase.setAccountNum(getAccountSession(session).getId());
          customerPurchase.setCart(getSessionCart(session));
          customerPurchase.setTotal(getSessionCart(session).total());
          customerPurchase.setCreated(LocalDate.now());
          customerPurchase.setCardCircuit(request.getParameter("circuit"));
          customerPurchase.setPanCard(Long.parseLong(request.getParameter("number")));
          if (purchaseDao.createPurchase(customerPurchase)) {
            getSessionCart(session).resetCart();
            response.sendRedirect("/LArteDelMangiare_war_exploded/purchases/profile");
          } else {
            internalError();
          }
          break;
        default:
          notAllowed();
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
