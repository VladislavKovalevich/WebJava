
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.model.Country;

import java.util.List;


public class CountryFileService extends FileService<Country> implements CountryService {

    public CountryFileService(final String file) {
        super(file);
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
    protected String convertToString(Country country) {
        StringBuffer buffer = new StringBuffer().
                append(country.getId()).append("|").
                append(country.getCountryName());

        return buffer.toString();
    }

    @Override
    protected Country convertToObject(String line) {
        if ((line != null) && (line.trim().length() > 0)) {
            String[] parts = line.split("\\|");

            Country country = new Country();
            country.setId(Long.parseLong(parts[0]));
            country.setCountryName(parts[1]);

            return country;
        }

        return null;
    }

}
