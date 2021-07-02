package Model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ProductSearch implements SearchBuilder {

  @Override
  public List<Condition> buildSearch(HttpServletRequest request) {
    List<Condition> conditions = new ArrayList<>();
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String param = parameterNames.nextElement();
      String value = request.getParameter(param);
      if (value != null && !value.isBlank()) {
        switch (param) {
          case "prodname":
            conditions.add(new Condition("prodname", Operator.MATCH, value));
            break;
          case "countryId":
            conditions.add(new Condition("country_fk", Operator.EQ, value));
            break;
          case "categoryId":
            conditions.add(new Condition("category_fk", Operator.EQ, value));
            break;
          case "minPrice":
            conditions.add(new Condition("price", Operator.GT, value));
            break;
          case "maxPrice":
            conditions.add(new Condition("price", Operator.LT, value));
            break;
          default:
            break;
        }
      }
    }
    return conditions;
  }
}
