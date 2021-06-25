package Controller;

import Model.Paginator;
import Model.Product;
import Model.ProductDao;
import Model.SqlProductDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", value = "/products/*")
@MultipartConfig
public class ProductServlet extends Controller implements ErrorHandler {

  private ProductDao<SQLException> productDao;

  @Override
  public void init() throws ServletException {
    super.init();
    productDao = new SqlProductDAO(source);
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
          size = productDao.countAll();
          request.setAttribute("pages", paginator.getPages(size));
          List<Product> products = null;
          products = productDao.fetchProducts(paginator);
          request.setAttribute("products", products);
          request.getRequestDispatcher(view("crm/products")).forward(request, response);
          break;
        case "/show":
          /*authorize(request.getSession(false));*/
          validate(CommonValidator.validateId(request));
          int id = Integer.parseInt(request.getParameter("id"));
          Optional<Product> optionalProduct = productDao.fetchProduct(id);
          if (optionalProduct.isPresent()) {
            request.setAttribute("product", optionalProduct.get());
            request.getRequestDispatcher(view("crm/product")).forward(request, response);
          } else {
            notFound();
          }
          break;
        case "/create":
          /*authorize(request.getSession(false));*/
          request.getRequestDispatcher(view("crm/product")).forward(request, response);
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
          authorize(request.getSession(false));
          request.setAttribute("back", view("crm/product"));
          validate(ProductValidator.validateForm(request));
          /* Product product = new Product*/
          break;
      }
    } catch (InvalidRequestException ex) {
      log(ex.getMessage());
      ex.handle(request, response);
    }
  }
}
