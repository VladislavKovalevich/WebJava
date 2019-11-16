
package org.labs.wt.tour;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.control.TourServices;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Region;

import java.util.List;


public class ApplFileTests {

    private CountryService countryFileService = TourServices.getFileServices().getCountryService();
    private RegionService regionFileService = TourServices.getFileServices().getRegionService();


    public static void main(String[] args) {
        ApplFileTests tests = new ApplFileTests();

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
        countryFileService.addCountry(country);

        country.setId(2);
        country.setCountryName("Greece");
        countryFileService.addCountry(country);

        country.setId(3);
        country.setCountryName("Belarus");
        countryFileService.addCountry(country);

        country.setId(4);
        country.setCountryName("Russia");
        countryFileService.addCountry(country);
    }

    private void addRegions() {
        Country country = countryFileService.getCountryById(1);

        Region region = new Region();
        region.setId(1);
        region.setRegionName("Turkey-1");
        region.setCountry(country);
        regionFileService.addRegion(region);

        region.setId(2);
        region.setRegionName("Turkey-2");
        region.setCountry(country);
        regionFileService.addRegion(region);

        country = countryFileService.getCountryById(4);
        region.setId(3);
        region.setRegionName("Russia-1");
        region.setCountry(country);
        regionFileService.addRegion(region);

        region.setId(4);
        region.setRegionName("Russia-2");
        region.setCountry(country);
        regionFileService.addRegion(region);
    }

    private void updateCountries() {
        Country country = countryFileService.getCountryById(2);
        country.setCountryName("Греция");
        countryFileService.updateCountry(country);

        country = countryFileService.getCountryById(3);
        country.setCountryName("Беларусь");
        countryFileService.updateCountry(country);
    }

    private void updateRegions() {
        Region region = regionFileService.getRegionById(2);
        region.setRegionName("Turkey-3");
        regionFileService.updateRegion(region);

        region = regionFileService.getRegionById(4);
        region.setRegionName("Russia-3");
        regionFileService.updateRegion(region);
    }

    private void deleteCountries() {
        Country country = countryFileService.getCountryById(2);
        countryFileService.deleteCountry(country.getId());

        country = countryFileService.getCountryById(3);
        countryFileService.deleteCountry(country.getId());
    }

    private void deleteRegions() {
        Region region = regionFileService.getRegionById(1);
        regionFileService.deleteRegion(region.getId());

        region = regionFileService.getRegionById(4);
        regionFileService.deleteRegion(region.getId());
    }

    private void printCountries() {
        List<Country> list = countryFileService.getCountries();
        System.out.println("Countries:");
        for (Country country : list) {
            System.out.println("id: " + country.getId() + "; name: " + country.getCountryName());
        }
    }

    private void printRegions() {
        List<Region> list = regionFileService.getRegions();
        System.out.println("Regions:");
        for (Region region : list) {
            System.out.println("id: " + region.getId() + "; name: " + region.getRegionName());
        }
    }

}
