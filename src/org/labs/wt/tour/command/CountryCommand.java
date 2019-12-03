
package org.labs.wt.tour.command;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.model.Country;

import java.util.List;


public class CountryCommand extends AbstractCommand {

    public CountryCommand() {
        super("country");
    }


    public boolean processCommand(String[] params) {
        if (params.length < 2) {
            System.out.println("invalid country params");
            return false;
        }

        if (super.processCommand(params)) {
            return true;
        }

        switch (params[1]) {
            case "add":
                addCountry(params);
                break;

            case "update":
                updateCountry(params);
                break;

            case "delete":
                deleteCountry(params);
                break;

            case "getall":
                getAllCountries(params);
                break;

            case "getbyid":
                getByIdCountry(params);
                break;

            case "filter":
                filterCountries(params);
                break;

            default:
                System.out.println("unknown country command");
                return false;
        }

        return true;
    }

    protected void printHelp() {
        super.printHelp();
        System.out.println("  country add <id> <name>");
        System.out.println("  country update <id> <name>");
        System.out.println("  country filter <name>");
    }

    private void addCountry(String[] params) {
        getCountryService().addCountry(getObjectFromParams(params));
    }

    private void updateCountry(String[] params) {
        getCountryService().updateCountry(getObjectFromParams(params));
    }

    private void deleteCountry(String[] params) {
        if (params.length > 2) {
            boolean result = getCountryService().deleteCountry(Long.parseLong(params[2]));
            if (result) {
                System.out.println("country deleted");
            } else {
                System.out.println("country not found");
            }
        }
    }

    private void getAllCountries(String[] params) {
        List<Country> countries = getCountryService().getCountries();
        for (Country country: countries) {
            printCountry(country);
        }
    }

    private void getByIdCountry(String[] params) {
        if (params.length > 2) {
            Country country = getCountryService().getCountryById(Long.parseLong(params[2]));
            printCountry(country);
        }
    }

    private void filterCountries(String[] params) {
        if (params.length < 3) {
            return;
        }

        Country filter = new Country();
        filter.setCountryName(params[2]);

        List<Country> filtered = getCountryService().filterCountries(filter);
        for (Country country : filtered) {
            printCountry(country);
        }
    }

    private Country getObjectFromParams(String[] params) {
        Country country = new Country();

        if (params.length > 2) {
            country.setId(Long.parseLong(params[2]));
        }
        if (params.length > 3) {
            country.setCountryName(params[3]);
        }

        return country;
    }

    private void printCountry(Country country) {
        if (country != null) {
            System.out.println("id: " + country.getId() + "; name: " + country.getCountryName());
        }
    }

    private CountryService getCountryService() {
        return getTourServicesFactory().getCountryService();
    }

}
