package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PagesServlet", value = "/pages/*")
public class PagesServlet extends Controller implements ErrorHandler {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String path = getPath(request);
      switch (path) {
        case "/":
          request.getRequestDispatcher(view("site/home")).forward(request, response);
          break;
        case "/dashboard":
          authorize(request.getSession(false));
          request.getRequestDispatcher(view("crm/home")).forward(request, response);
          break;
        default:
          notFound();
      }
    } catch (InvalidRequestException ex) {
      log(ex.getMessage());
      ex.handle(request, response);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}
}
