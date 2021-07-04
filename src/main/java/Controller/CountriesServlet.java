package Controller;

import Model.Alert;
import Model.Country;
import Model.CountryDao;
import Model.Paginator;
import Model.SqlCountryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
        case "/show":
          /*authorize(request.getSession(false));*/
          validate(CommonValidator.validateId(request));
          int id = Integer.parseInt(request.getParameter("id"));
          Optional<Country> optionalCountry = countryDao.fetchCountry(id);
          if (optionalCountry.isPresent()) {
            request.setAttribute("country", optionalCountry.get());
            request.getRequestDispatcher(view("crm/country")).forward(request, response);
          } else {
            notFound();
          }
          break;
        case "/create":
          /*authorize(request.getSession(false));*/
          request.getRequestDispatcher(view("crm/country")).forward(request, response);
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
          request.setAttribute("back", view("crm/country"));
          validate(CountryValidator.validateForm(request));
          Country country = new Country();
          country.setLabel(request.getParameter("label"));
          if (countryDao.createCountries(country)) {
            request.setAttribute("alert", new Alert(List.of("Country Created!"), "success"));
            response.setStatus(HttpServletResponse.SC_CREATED);
            request.getRequestDispatcher(view("crm/country")).forward(request, response);
          } else {
            internalError();
          }
          break;
        case "/update":
          authorize(request.getSession(false));
          request.setAttribute("back", view("crm/country"));
          validate(CountryValidator.validateForm(request));
          Country countryup = new Country();
          countryup.setId(Integer.parseInt(request.getParameter("id")));
          countryup.setLabel(request.getParameter("label"));
          request.setAttribute("country", countryup);
          if (countryDao.updateCountries(countryup)) {
            request.setAttribute("alert", new Alert(List.of("Country Updated!"), "success"));
            request.getRequestDispatcher(view("crm/country")).forward(request, response);
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
