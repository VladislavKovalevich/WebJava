
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.model.Region;

import java.util.List;


public class RegionDBService extends DBService<Region> implements RegionService {


    public RegionDBService() {
        super("region");
    }

    @Override
    public List<Region> getRegions() {
        return getAllObjects();
    }

    @Override
    public Region getRegionById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addRegion(Region region) {
        return addObject(region);
    }

    @Override
    public boolean updateRegion(Region region) {
        return updateObject(region);
    }

    @Override
    public boolean deleteRegion(long id) {
        return deleteObjectByID(id);
    }

    @Override
    public List<Region> filterRegions(Region filter) {
        return filterObjects(null);
    }

}
