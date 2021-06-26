package Controller;

import Model.Category;
import Model.Country;
import Model.Paginator;
import Model.Product;
import Model.ProductDao;
import Model.SqlProductDAO;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
          authorize(request.getSession(false));
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
          /*authorize(request.getSession(false));*/
          request.setAttribute("back", view("crm/product"));
          validate(ProductValidator.validateForm(request));
          Product product = new Product();
          product.setPrice(Double.parseDouble(request.getParameter("price")));
          product.setProdName(request.getParameter("fullName"));
          product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
          product.setLabel(request.getParameter("description"));
          Part filePart = request.getPart("cover");
          String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
          product.setImage(fileName);
          Category category = new Category();
          category.setId(Integer.parseInt(request.getParameter("catId")));
          Country country = new Country();
          country.setId(Integer.parseInt(request.getParameter("couId")));
          product.setCategory(category);
          product.setCountry(country);
          if (productDao.createProduct(product)) {
            String uploadRoot = getUploadPath();
            product.writeCover(uploadRoot, filePart);
            request.getRequestDispatcher(view("crm/products")).forward(request, response);
          } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server Error");
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
