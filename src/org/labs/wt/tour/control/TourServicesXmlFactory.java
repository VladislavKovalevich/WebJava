
package org.labs.wt.tour.control;


import org.labs.wt.tour.control.xml.CountryXmlService;
import org.labs.wt.tour.control.xml.RegionXmlService;

import java.io.File;


public class TourServicesXmlFactory implements TourServicesFactory {

    private static final TourServicesXmlFactory instance = new TourServicesXmlFactory("xml");

    private final String dir;

    private CountryService countryService = null;
    private RegionService regionService = null;


    static final TourServicesXmlFactory getInstance() {
        return instance;
    }

    private TourServicesXmlFactory(final String dir) {
        this.dir = dir;
    }

    @Override
    public CountryService getCountryService() {
        if (countryService == null) {
            countryService = new CountryXmlService(dir + File.pathSeparator + "country.xml");
        }

        return countryService;
    }

    @Override
    public RegionService getRegionService() {
        if (regionService == null) {
            regionService = new RegionXmlService(dir + File.pathSeparator + "region.xml");
        }

        return regionService;
    }

}
