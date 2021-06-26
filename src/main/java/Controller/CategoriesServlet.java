package Controller;

import Model.Category;
import Model.CategoryDao;
import Model.Paginator;
import Model.SqlCategoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriesServlet", value = "/categories/*")
public class CategoriesServlet extends Controller implements ErrorHandler {

  private CategoryDao<SQLException> categoryDao;

  @Override
  public void init() throws ServletException {
    super.init();
    categoryDao = new SqlCategoryDAO(source);
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
          size = categoryDao.countAll();
          request.setAttribute("pages", paginator.getPages(size));
          List<Category> categories = null;
          categories = categoryDao.fetchCategories(paginator);
          request.setAttribute("categories", categories);
          request.getRequestDispatcher(view("crm/categories")).forward(request, response);
          break;
      }
    } catch (SQLException ex) {
      log(ex.getMessage());
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}
}
