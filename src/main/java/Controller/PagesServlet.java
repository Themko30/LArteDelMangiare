package Controller;

import Model.AccountDao;
import Model.Category;
import Model.CategoryDao;
import Model.Condition;
import Model.Paginator;
import Model.Product;
import Model.ProductDao;
import Model.ProductSearch;
import Model.SqlAccountDAO;
import Model.SqlCategoryDAO;
import Model.SqlProductDAO;
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
  private AccountDao<SQLException> accountDao;
  private ProductDao<SQLException> productDao;

  @Override
  public void init() throws ServletException {
    super.init();
    categoryDao = new SqlCategoryDAO(source);
    accountDao = new SqlAccountDAO(source);
    productDao = new SqlProductDAO(source);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = getPath(request);
      switch (path) {
        case "/home":
          int intPageHome = parsePage(request);
          Paginator paginatorHome = new Paginator(intPageHome, 30);
          int sizeHome = 0;
          sizeHome = categoryDao.countAll();
          request.setAttribute("pages", paginatorHome.getPages(sizeHome));
          List<Category> categories = null;
          categories = categoryDao.fetchCategories(paginatorHome);
          getServletContext().setAttribute("categories", categories);
          request.getRequestDispatcher(view("site/home")).forward(request, response);
          break;
        case "/info/privacy":
          request.getRequestDispatcher(view("site/privacy")).forward(request, response);
          break;
        case "/info/aboutme":
          request.getRequestDispatcher(view("site/aboutMe")).forward(request, response);
          break;
        case "/products":
          int intPagePro = parsePage(request);
          Paginator paginatorProd = new Paginator(intPagePro, 30);
          int sizePro = 0;
          sizePro = productDao.countAll();
          request.setAttribute("pages", paginatorProd.getPages(sizePro));
          List<Product> products = null;
          products = productDao.fetchProductsWithRelations(paginatorProd);
          request.setAttribute("products", products);
          request.getRequestDispatcher(view("site/products")).forward(request, response);
          break;
        case "/category":
          int intPageCat = parsePage(request);
          Paginator paginatorCat = new Paginator(intPageCat, 30);
          int sizeCat = 0;
          int catId = Integer.parseInt(request.getParameter("catId"));
          sizeCat = productDao.countAllByCat(catId);
          request.setAttribute("pages", paginatorCat.getPages(sizeCat));
          List<Product> productsCat = null;
          productsCat = productDao.fetchProductsByCat(catId, paginatorCat);
          request.setAttribute("products", productsCat);
          request.getRequestDispatcher(view("site/category")).forward(request, response);
          break;
        case "/search":
          List<Condition> conditions = new ProductSearch().buildSearch(request);
          List<Product> searchProducts =
              conditions.isEmpty()
                  ? productDao.fetchProductsWithRelations(new Paginator(1, 150))
                  : productDao.search(conditions);
          request.setAttribute("products", searchProducts);
          request.getRequestDispatcher(view("site/search")).forward(request, response);
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
      throws ServletException, IOException {

    doGet(request, response);
  }
}
