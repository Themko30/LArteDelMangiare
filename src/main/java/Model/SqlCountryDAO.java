package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlCountryDAO extends SqlDao implements CountryDao<SQLException> {

  protected SqlCountryDAO(DataSource source) {
    super(source);
  }

  @Override
  public List<Country> fetchCountries() throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("country", "cou");
      queryBuilder.select();
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ResultSet set = ps.executeQuery();
        CountryExtractor countryExtractor = new CountryExtractor();
        List<Country> country = new ArrayList<>();
        while (set.next()) {
          country.add(countryExtractor.extract(set));
        }
        return country;
      }
    }
  }

  @Override
  public Optional<Country> fetchCountry(int id) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("country", "cou");
      String query = queryBuilder.select().where("cou.id=?").generateQuery();
      try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        Country country = null;
        if (set.next()) {
          country = new CountryExtractor().extract(set);
        }
        return Optional.ofNullable(country);
      }
    }
  }

  @Override
  public boolean createCountries(Country country) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("country", "cou");
      queryBuilder.insert("label");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setString(1, country.getLabel());
        int row = ps.executeUpdate();
        return row == 1;
      }
    }
  }

  @Override
  public boolean updateCountries(Country country) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("country", "cou");
      queryBuilder.update("label").where("id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setString(1, country.getLabel());
        ps.setInt(2, country.getId());
        int row = ps.executeUpdate();
        return row == 1;
      }
    }
  }

  @Override
  public Optional<Country> fetchCountriesWithProducts(int countryId) throws SQLException {
    try (Connection conn = source.getConnection()) {
      QueryBuilder queryBuilder = new QueryBuilder("country", "cou");
      queryBuilder
          .select()
          .innerJoin("products", "pro")
          .on("cat.id = pro.country_fk")
          .where("cat.id=?");
      try (PreparedStatement ps = conn.prepareStatement(queryBuilder.generateQuery())) {
        ps.setInt(1, countryId);
        ResultSet set = ps.executeQuery();
        CountryExtractor countryExtractor = new CountryExtractor();
        Country country = null;
        if (set.next()) {
          country = countryExtractor.extract(set);
          country.setProducts(new ArrayList<>());
          ProductExtractor productExtractor = new ProductExtractor();
          country.getProducts().add(productExtractor.extract(set));
          while (set.next()) {
            country.getProducts().add(productExtractor.extract(set));
          }
        }
        return Optional.ofNullable(country);
      }
    }
  }
}
