
package org.labs.wt.tour.control;


import org.labs.wt.tour.model.Country;

import java.util.List;


public interface CountryService {

    List<Country> getCountries();

    Country getCountryById(long id);

    boolean addCountry(Country country);

    boolean updateCountry(Country country);

    boolean deleteCountry(long id);

}
