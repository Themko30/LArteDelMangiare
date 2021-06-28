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

@WebServlet(name = "PagesServlet", value = "/pages/*")
public class PagesServlet extends Controller implements ErrorHandler {

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
      String path = getPath(request);
      switch (path) {
        case "/":
          Paginator paginator = new Paginator(1, 30);
          int size = 0;
          size = categoryDao.countAll();
          request.setAttribute("pages", paginator.getPages(size));
          List<Category> categories = null;
          categories = categoryDao.fetchCategories(paginator);
          request.setAttribute("categories", categories);
          request.getRequestDispatcher(view("site/home")).forward(request, response);
          break;
        case "/dashboard":
          /*authorize(request.getSession(false));*/
          request.getRequestDispatcher(view("crm/home")).forward(request, response);
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
      throws ServletException, IOException {}
}
