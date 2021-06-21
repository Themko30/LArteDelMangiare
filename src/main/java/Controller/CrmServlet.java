package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CrmServlet", value = "/crm/*")
public class CrmServlet extends Controller {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String path = getPath(request);
    switch (path) {
      case "/dashboard":
        request.getRequestDispatcher(view("crm/home")).forward(request, response);
        break;
      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource Not Found");
    }
  }
}
