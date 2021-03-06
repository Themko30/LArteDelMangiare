package Model;

import java.util.List;
import java.util.Optional;

public interface CountryDao<E extends Exception> {

  List<Country> fetchCountries(Paginator paginator) throws E;

  Optional<Country> fetchCountry(int id) throws E;

  int countAll() throws E;

  boolean createCountries(Country country) throws E;

  boolean updateCountries(Country country) throws E;

  Optional<Country> fetchCountriesWithProducts(int countryId) throws E;
}
