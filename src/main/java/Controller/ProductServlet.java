package Controller;

import Model.Category;
import Model.Country;
import Model.Product;
import Model.ProductDao;
import Model.SqlProductDAO;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "ProductServlet", value = "/products/*")
@MultipartConfig
public class ProductServlet extends Controller {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String path = getPath(request);
    switch (path) {
      case "/":
        request.getRequestDispatcher(view("crm/products")).forward(request, response);
        break;
      case "/show":
        request.getRequestDispatcher(view("crm/product")).forward(request, response);
        break;
      case "/create":
        request.getRequestDispatcher(view("crm/product")).forward(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = getPath(request);
      switch (path) {
        case "/create":
          ProductDao<SQLException> productDao = new SqlProductDAO(source);
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
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            String uploadRoot = getUploadPath();
            product.writeCover(uploadRoot, filePart);
          } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server Error");
          }
          break;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
