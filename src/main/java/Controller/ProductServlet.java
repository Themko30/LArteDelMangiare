package Controller;

import Model.Paginator;
import Model.Product;
import Model.ProductDao;
import Model.SqlProductDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
          int size = productDao.countAll();
          request.setAttribute("pages", paginator.getPages(size));
          List<Product> products = productDao.fetchProducts(paginator);
          request.setAttribute("products", products);
          request.getRequestDispatcher(view("crm/products")).forward(request, response);
          break;
        case "/show":
          break;
        case "/create":
          request.getRequestDispatcher(view("crm/product")).forward(request, response);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
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
