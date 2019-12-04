
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.model.Country;

import java.sql.ResultSet;
import java.util.List;


public class CountryDBService extends DBService<Country> implements CountryService {


    public CountryDBService() {
        super("country");
    }


    private ResultSetListener<Country> resultSetListener = new ResultSetListener<Country>() {
        @Override
        public Country onResultSet(ResultSet resultSet) {
            try {
                Country country = new Country();
                country.setId(resultSet.getInt("country_id"));
                country.setCountryName(resultSet.getString("country_name"));

                return country;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    };

    @Override
    public List<Country> getCountries() {
        return getAllObjects(resultSetListener);
    }

    @Override
    public Country getCountryById(long id) {
        return getObjectByID(id, "country_id", resultSetListener);
    }

    @Override
    public boolean addCountry(Country country) {
        String sql = "insert into country (country_id, country_name) values (?, ?)";
        return addObject(sql, preparedStatement -> {
            try {
                preparedStatement.setLong(1, country.getId());
                preparedStatement.setString(2, country.getCountryName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean updateCountry(Country country) {
        String sql = "update country set country_name = ? where country_id = ?";
        return updateObject(sql, preparedStatement -> {
            try {
                preparedStatement.setString(1, country.getCountryName());
                preparedStatement.setLong(2, country.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean deleteCountry(long id) {
        return deleteObjectByID(id, "country_id");
    }

    @Override
    public List<Country> filterCountries(Country country) {
        return filterObjects(null);
    }

}
