
package org.labs.wt.tour.control;


import org.labs.wt.tour.control.file.CountryFileService;
import org.labs.wt.tour.control.file.RegionFileService;

import java.io.File;


public class TourServicesFileFactory implements TourServicesFactory {

    private static final TourServicesFileFactory instance = new TourServicesFileFactory("file");

    private final String dir;

    private CountryService countryService = null;
    private RegionService regionService = null;


    static final TourServicesFileFactory getInstance() {
        return instance;
    }

    private TourServicesFileFactory(final String dir) {
        this.dir = "data" + File.separator + dir;
    }

    @Override
    public CountryService getCountryService() {
        if (countryService == null) {
            countryService = new CountryFileService(dir + File.separator + "country.txt");
        }

        return countryService;
    }

    @Override
    public RegionService getRegionService() {
        if (regionService == null) {
            regionService = new RegionFileService(dir + File.separator + "region.txt");
        }

        return regionService;
    }

}
