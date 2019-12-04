
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.FilterListener;
import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Region;

import java.util.List;


public class RegionFileService extends FileService<Region> implements RegionService {

    public RegionFileService(final String file) {
        super(file);
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
        return filterObjects(
                (FilterListener<Region>) region -> {
                    boolean res1 = true;
                    if ((region.getRegionName() != null) &&
                            (filter.getRegionName() != null) &&
                            (!region.getRegionName().contains(filter.getRegionName()))) {
                        res1 = false;
                    }

                    boolean res2 = true;
                    if ((region.getCountry() != null) &&
                            (filter.getCountry() != null) &&
                            (region.getCountry().getId() != filter.getCountry().getId())) {
                        res2 = false;
                    }

                    return res1 & res2;
                });
    }

    @Override
    protected String convertToString(Region region) {
        StringBuffer buffer = new StringBuffer().
                append(region.getId()).append("|").
                append(region.getRegionName()).append("|").
                append(region.getCountry() != null ? region.getCountry().getId() : -1);

        return buffer.toString();
    }

    @Override
    protected Region convertToObject(String line) {
        if ((line != null) && (line.trim().length() > 0)) {
            String[] parts = line.split("\\|");

            Region region = new Region();
            region.setId(Long.parseLong(parts[0]));
            region.setRegionName(parts[1]);

            Country country = new Country();
            country.setId(Long.parseLong(parts[2]));
            region.setCountry(country);

            return region;
        }

        return null;
    }

}
