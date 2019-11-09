
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.model.Country;

import java.util.ArrayList;
import java.util.List;


public class CountryFileService extends FileService implements CountryService {

    public CountryFileService(final String file) {
        super(file);
    }

    @Override
    public List<Country> getCountries() {
        List<Country> list = new ArrayList<>();

        checkFile(new LineListener() {
            @Override
            public boolean checkLine(String line) {
                if ((line != null) && (line.trim().length() > 0)) {
                    String[] parts = line.split("\\|");

                    Country country = new Country();
                    country.setId(Long.parseLong(parts[0]));
                    country.setCountryName(parts[1]);

                    list.add(country);
                }



                return true;
            }
        });

        return list;
    }

    @Override
    public Country getCountryById(long id) {
        final Country country = new Country();

        checkFile(new LineListener() {
            @Override
            public boolean checkLine(String line) {
                if ((line != null) && (line.trim().length() > 0)) {
                    String[] parts = line.split("\\|");

                    long key = Long.parseLong(parts[0]);
                    if (key == id) {
                        country.setId(Long.parseLong(parts[0]));
                        country.setCountryName(parts[1]);

                        return false;
                    }
                }

                return true;
            }
        });

        return country;
    }

    @Override
    public boolean addCountry(Country country) {
        StringBuffer buffer = new StringBuffer().
                append(country.getId()).append("|").
                append(country.getCountryName());

        appendRecord(buffer.toString());

        return true;
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
