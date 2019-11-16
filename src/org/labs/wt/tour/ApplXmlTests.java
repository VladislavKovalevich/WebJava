
package org.labs.wt.tour;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.control.TourServices;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Region;

import java.util.List;


public class ApplXmlTests {

    private CountryService countryXmlService = TourServices.getXmlServices().getCountryService();
    private RegionService regionXmlService = TourServices.getXmlServices().getRegionService();


    public static void main(String[] args) {
        ApplXmlTests tests = new ApplXmlTests();

        tests.testCountries();
        tests.testRegions();
    }

    private void testCountries() {
        addCountries();
        printCountries();
        updateCountries();
        printCountries();
        deleteCountries();
        printCountries();
    }

    private void testRegions() {
        addRegions();
        printRegions();
        updateRegions();
        printRegions();
        deleteRegions();
        printRegions();
    }

    private void addCountries() {
        Country country = new Country();
        country.setId(1);
        country.setCountryName("Turkey");
        countryXmlService.addCountry(country);

        country.setId(2);
        country.setCountryName("Greece");
        countryXmlService.addCountry(country);

        country.setId(3);
        country.setCountryName("Belarus");
        countryXmlService.addCountry(country);

        country.setId(4);
        country.setCountryName("Russia");
        countryXmlService.addCountry(country);
    }

    private void addRegions() {
        Country country = countryXmlService.getCountryById(1);

        Region region = new Region();
        region.setId(1);
        region.setRegionName("Turkey-1");
        region.setCountry(country);
        regionXmlService.addRegion(region);

        region.setId(2);
        region.setRegionName("Turkey-2");
        region.setCountry(country);
        regionXmlService.addRegion(region);

        country = countryXmlService.getCountryById(4);
        region.setId(3);
        region.setRegionName("Russia-1");
        region.setCountry(country);
        regionXmlService.addRegion(region);

        region.setId(4);
        region.setRegionName("Russia-2");
        region.setCountry(country);
        regionXmlService.addRegion(region);
    }

    private void updateCountries() {
        Country country = countryXmlService.getCountryById(2);
        country.setCountryName("Греция");
        countryXmlService.updateCountry(country);

        country = countryXmlService.getCountryById(3);
        country.setCountryName("Беларусь");
        countryXmlService.updateCountry(country);
    }

    private void updateRegions() {
        Region region = regionXmlService.getRegionById(2);
        region.setRegionName("Turkey-3");
        regionXmlService.updateRegion(region);

        region = regionXmlService.getRegionById(4);
        region.setRegionName("Russia-3");
        regionXmlService.updateRegion(region);
    }

    private void deleteCountries() {
        Country country = countryXmlService.getCountryById(2);
        countryXmlService.deleteCountry(country.getId());

        country = countryXmlService.getCountryById(3);
        countryXmlService.deleteCountry(country.getId());
    }

    private void deleteRegions() {
        Region region = regionXmlService.getRegionById(1);
        regionXmlService.deleteRegion(region.getId());

        region = regionXmlService.getRegionById(4);
        regionXmlService.deleteRegion(region.getId());
    }

    private void printCountries() {
        List<Country> list = countryXmlService.getCountries();
        System.out.println("Countries:");
        for (Country country : list) {
            System.out.println("id: " + country.getId() + "; name: " + country.getCountryName());
        }
    }

    private void printRegions() {
        List<Region> list = regionXmlService.getRegions();
        System.out.println("Regions:");
        for (Region region : list) {
            System.out.println("id: " + region.getId() + "; name: " + region.getRegionName());
        }
    }

}
