
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.model.Country;

import java.util.List;


public class CountryDBService extends DBService<Country> implements CountryService {


    public CountryDBService() {
        super("country");
    }

    @Override
    public List<Country> getCountries() {
        return getAllObjects();
    }

    @Override
    public Country getCountryById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addCountry(Country country) {
        return addObject(country);
    }

    @Override
    public boolean updateCountry(Country country) {
        return updateObject(country);
    }

    @Override
    public boolean deleteCountry(long id) {
        return deleteObjectByID(id);
    }

    @Override
    public List<Country> filterCountries(Country country) {
        return filterObjects(null);
    }

}
