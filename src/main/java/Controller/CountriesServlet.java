package Controller;

import Model.Country;
import Model.CountryDao;
import Model.Paginator;
import Model.SqlCountryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CountriesServlet", value = "/countries/*")
public class CountriesServlet extends Controller implements ErrorHandler {

  private CountryDao<SQLException> countryDao;

  @Override
  public void init() throws ServletException {
    super.init();
    countryDao = new SqlCountryDAO(source);
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
          size = countryDao.countAll();
          request.setAttribute("pages", paginator.getPages(size));
          List<Country> countries = null;
          countries = countryDao.fetchCountries(paginator);
          request.setAttribute("countries", countries);
          request.getRequestDispatcher(view("crm/countries")).forward(request, response);
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
