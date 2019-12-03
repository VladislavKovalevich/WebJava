
package org.labs.wt.tour.command;


import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Region;

import java.util.List;


public class RegionCommand extends AbstractCommand {


    public RegionCommand() {
        super("region");
    }


    public boolean processCommand(String[] params) {
        if (params.length < 2) {
            System.out.println("invalid region params");
            return false;
        }

        if (super.processCommand(params)) {
            return true;
        }

        switch (params[1]) {
            case "add":
                addRegion(params);
                break;

            case "update":
                updateRegion(params);
                break;

            case "delete":
                deleteRegion(params);
                break;

            case "getall":
                getAllRegions(params);
                break;

            case "getbyid":
                getByIdRegion(params);
                break;

            case "filter":
                filterRegions(params);
                break;

            default:
                System.out.println("unknown region command");
                return false;
        }

        return true;
    }

    protected void printHelp() {
        super.printHelp();
        System.out.println("  region add <id> <name> <countryID>");
        System.out.println("  region update <id> <name> <countryID>");
        System.out.println("  region filter <name> <countryID>");
    }

    private void addRegion(String[] params) {
        getRegionService().addRegion(getObjectFromParams(params));
    }

    private void updateRegion(String[] params) {
        getRegionService().updateRegion(getObjectFromParams(params));
    }

    private void deleteRegion(String[] params) {
        if (params.length > 2) {
            boolean result = getRegionService().deleteRegion(Long.parseLong(params[2]));
            if (result) {
                System.out.println("country deleted");
            } else {
                System.out.println("country not found");
            }
        }
    }

    private void getAllRegions(String[] params) {
        List<Region> regions = getRegionService().getRegions();
        for (Region region : regions) {
            printRegion(region);
        }
    }

    private void getByIdRegion(String[] params) {
        if (params.length > 2) {
            Region region = getRegionService().getRegionById(Long.parseLong(params[2]));
            printRegion(region);
        }
    }

    private void filterRegions(String[] params) {
        if (params.length < 3) {
            return;
        }

        Region filter = new Region();
        filter.setRegionName(params[2]);

        if ((params.length > 3) && (!params[3].equals("-1"))) {
            filter.setCountry(new Country());
            filter.getCountry().setId(Long.parseLong(params[3]));
        }

        List<Region> filtered = getRegionService().filterRegions(filter);
        for (Region region : filtered) {
            printRegion(region);
        }
    }

    private Region getObjectFromParams(String[] params) {
        Region region = new Region();

        if (params.length > 2) {
            region.setId(Long.parseLong(params[2]));
        }
        if (params.length > 3) {
            region.setRegionName(params[3]);
        }
        if (params.length > 4) {
            region.setCountry(new Country());
            region.getCountry().setId(Long.parseLong(params[4]));
        }

        return region;
    }

    private void printRegion(Region region) {
        if (region != null) {
            System.out.println("id: " + region.getId() + "; name: " + region.getRegionName() + "; countryID: " + region.getCountry().getId());
        }
    }

    private RegionService getRegionService() {
        return getTourServicesFactory().getRegionService();
    }

}
