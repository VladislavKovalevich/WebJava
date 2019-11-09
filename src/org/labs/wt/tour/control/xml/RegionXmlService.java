
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.model.Region;

import java.util.List;


public class RegionXmlService implements RegionService {

    private final String file;

    public RegionXmlService(final String file) {
        this.file = file;
    }

    @Override
    public List<Region> getRegions() {
        return null;
    }

    @Override
    public Region getRegionById(long id) {
        return null;
    }

    @Override
    public boolean addRegion(Region region) {
        return false;
    }

    @Override
    public boolean updateRegion(Region region) {
        return false;
    }

    @Override
    public boolean deleteRegion(long id) {
        return false;
    }

}
