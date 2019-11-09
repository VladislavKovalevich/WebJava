
package org.labs.wt.tour.control;


import org.labs.wt.tour.model.Region;

import java.util.List;


public interface RegionService {

    public List<Region> getRegions();

    public Region getRegionById(long id);

    public boolean addRegion(Region region);

    boolean updateRegion(Region region);

    boolean deleteRegion(long id);

}
