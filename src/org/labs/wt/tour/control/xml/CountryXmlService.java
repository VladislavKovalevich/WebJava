
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.model.Country;

import java.util.List;


public class CountryXmlService implements CountryService {

    private final String file;

    public CountryXmlService(final String file) {
        this.file = file;
    }

    @Override
    public List<Country> getCountries() {
        return null;
    }

    @Override
    public Country getCountryById(long id) {
        return null;
    }

    @Override
    public boolean addCountry(Country country) {
        return false;
    }

    @Override
    public boolean updateCountry(Country country) {
        return false;
    }

    @Override
    public boolean deleteCountry(long id) {
        return false;
    }

}
