
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Region;

import java.util.ArrayList;
import java.util.List;


public class RegionFileService extends FileService implements RegionService {

    public RegionFileService(final String file) {
        super(file);
    }

    @Override
    public List<Region> getRegions() {
        List<Region> list = new ArrayList<>();

        checkFile(new LineListener() {
            @Override
            public boolean checkLine(String line) {
                if ((line != null) && (line.trim().length() > 0)) {
                    String[] parts = line.split("\\|");

                    Region region = new Region();
                    region.setId(Long.parseLong(parts[0]));
                    region.setRegionName(parts[1]);

                    Country country = new Country();
                    country.setId(Long.parseLong(parts[2]));

                    region.setCountry(country);

                    list.add(region);
                }

                return true;
            }
        });

        return list;
    }

    @Override
    public Region getRegionById(long id) {
        final Region region = new Region();

        checkFile(new LineListener() {
            @Override
            public boolean checkLine(String line) {
                if ((line != null) && (line.trim().length() > 0)) {
                    String[] parts = line.split("\\|");

                    long key = Long.parseLong(parts[0]);
                    if (key == id) {
                        region.setId(key);
                        region.setRegionName(parts[1]);

                        Country country = new Country();
                        country.setId(Long.parseLong(parts[2]));

                        region.setCountry(country);

                        return false;
                    }
                }

                return true;
            }
        });

        return region;
    }

    @Override
    public boolean addRegion(Region region) {
        StringBuffer buffer = new StringBuffer().
                append(region.getId()).append("|").
                append(region.getRegionName()).append("|").
                append(region.getCountry() != null ? region.getCountry().getId() : -1);

        appendRecord(buffer.toString());

        return true;
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
