package Controller;

import Model.AccountSession;
import Model.Cart;
import java.io.File;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public abstract class Controller extends HttpServlet {

  @Resource(name = "jdbc/project_tsw")
  protected static DataSource source;

  protected String getPath(HttpServletRequest req) {
    return req.getPathInfo() != null ? req.getPathInfo() : "/";
  }

  protected String view(String viewPath) {
    String basePath = getServletContext().getInitParameter("basePath");
    String engine = getServletContext().getInitParameter("engine");
    return basePath + viewPath + engine;
  }

  protected String back(HttpServletRequest request) {
    return request.getServletPath() + request.getPathInfo();
  }

  protected void validate(RequestValidator validator) throws InvalidRequestException {
    if (validator.hasErrors()) {
      throw new InvalidRequestException(
          "Validation Error", validator.getErrors(), HttpServletResponse.SC_BAD_REQUEST);
    }
  }

  protected String getUploadPath() {
    return System.getenv("CATALINA_HOME") + File.separator + "uploads" + File.separator;
  }

  protected int parsePage(HttpServletRequest request) {
    return Integer.parseInt(request.getParameter("page"));
  }

  protected AccountSession getAccountSession(HttpSession session) {
    return (AccountSession) session.getAttribute("accountSession");
  }

  protected Cart getSessionCart(HttpSession session) {
    return (Cart) session.getAttribute("accountCart");
  }
}
