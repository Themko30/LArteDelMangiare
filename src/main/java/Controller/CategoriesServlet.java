package Controller;

import Model.Alert;
import Model.Category;
import Model.CategoryDao;
import Model.Paginator;
import Model.SqlCategoryDAO;
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
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "CategoriesServlet", value = "/categories/*")
@MultipartConfig
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
      String path = getPath(request);
      ;
      switch (path) {
        case "/":
          authorize(request.getSession(false));
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
        case "/show":
          authorize(request.getSession(false));
          validate(CommonValidator.validateId(request));
          int id = Integer.parseInt(request.getParameter("id"));
          Optional<Category> optionalCategory = categoryDao.fetchCategory(id);
          if (optionalCategory.isPresent()) {
            request.setAttribute("category", optionalCategory.get());
            request.getRequestDispatcher(view("crm/category")).forward(request, response);
          } else {
            notFound();
          }
          break;
        case "/create":
          authorize(request.getSession(false));
          request.getRequestDispatcher(view("crm/category")).forward(request, response);
          break;
        case "/api":
          if (isAjax(request)) {
            List<Category> cat = categoryDao.fetchCategories(new Paginator(1, 50));
            JSONObject root = new JSONObject();
            JSONArray arr = new JSONArray();
            root.put("categories", arr);
            cat.forEach(c -> arr.put(c.toJson()));
            sendJson(response, root);
            break;
          } else {
            notFound();
          }
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
    try {
      String path = getPath(request);
      switch (path) {
        case "/create":
          authorize(request.getSession(false));
          request.setAttribute("back", view("crm/category"));
          validate(CategoryValidator.validateForm(request));
          Category category = new Category();
          category.setLabel(request.getParameter("label"));
          category.setDescription(request.getParameter("description"));
          Part filePart = request.getPart("cover");
          String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
          category.setImage(fileName);
          if (categoryDao.createCategories(category)) {
            String uploadRoot = getUploadPath();
            category.writeCover(uploadRoot, filePart);
            request.setAttribute("alert", new Alert(List.of("Category Created!"), "success"));
            response.setStatus(HttpServletResponse.SC_CREATED);
            request.getRequestDispatcher(view("crm/category")).forward(request, response);
          } else {
            internalError();
          }
          break;
        case "/update":
          authorize(request.getSession(false));
          request.setAttribute("back", view("crm/product"));
          validate(CategoryValidator.validateForm(request));
          Category categoryup = new Category();
          categoryup.setId(Integer.parseInt(request.getParameter("id")));
          categoryup.setLabel(request.getParameter("label"));
          categoryup.setDescription(request.getParameter("description"));
          Part filePartup = request.getPart("cover");
          String fileNameup = Paths.get(filePartup.getSubmittedFileName()).getFileName().toString();
          categoryup.setImage(fileNameup);
          request.setAttribute("category", categoryup);
          if (categoryDao.updateCategories(categoryup)) {
            String uploadRootup = getUploadPath();
            categoryup.writeCover(uploadRootup, filePartup);
            request.setAttribute("alert", new Alert(List.of("Category Updated!"), "success"));
            request.getRequestDispatcher(view("crm/category")).forward(request, response);
          } else {
            internalError();
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
