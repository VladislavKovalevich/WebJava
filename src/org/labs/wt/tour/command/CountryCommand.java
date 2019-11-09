
package org.labs.wt.tour.command;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.control.TourServices;


public class CountryCommand {

    public CountryCommand() {
        CountryService countryService = TourServices.getFileServices().getCountryService();
        RegionService regionService = TourServices.getXmlServices().getRegionService();
    }



}
