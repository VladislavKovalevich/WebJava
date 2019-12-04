
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Region;

import java.sql.ResultSet;
import java.util.List;


public class RegionDBService extends DBService<Region> implements RegionService {


    public RegionDBService() {
        super("region");
    }

    private ResultSetListener<Region> resultSetListener = new ResultSetListener<Region>() {
        @Override
        public Region onResultSet(ResultSet resultSet) {
            try {
                Region region = new Region();
                region.setId(resultSet.getInt("region_id"));
                region.setRegionName(resultSet.getString("region_name"));
                region.setCountry(new Country());
                region.getCountry().setId(resultSet.getLong("country_id"));

                return region;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    };

    @Override
    public List<Region> getRegions() {
        return getAllObjects(resultSetListener);
    }

    @Override
    public Region getRegionById(long id) {
        return getObjectByID(id, "region_id", resultSetListener);
    }

    @Override
    public boolean addRegion(Region region) {
        String sql = "insert into region (region_id, region_name, country_id) values (?, ?, ?)";
        return addObject(sql, preparedStatement -> {
            try {
                preparedStatement.setLong(1, region.getId());
                preparedStatement.setString(2, region.getRegionName());
                preparedStatement.setLong(3, region.getCountry().getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean updateRegion(Region region) {
        String sql = "update region set region_name = ?, country_id = ? where region_id = ?";
        return updateObject(sql, preparedStatement -> {
            try {
                preparedStatement.setString(1, region.getRegionName());
                preparedStatement.setLong(2, region.getCountry().getId());
                preparedStatement.setLong(3, region.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean deleteRegion(long id) {
        return deleteObjectByID(id, "region_id");
    }

    @Override
    public List<Region> filterRegions(Region filter) {
        return filterObjects(null);
    }

}
