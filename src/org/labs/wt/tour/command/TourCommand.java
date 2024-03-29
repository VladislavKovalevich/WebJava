
package org.labs.wt.tour.command;


import org.labs.wt.tour.control.TourService;
import org.labs.wt.tour.model.Client;
import org.labs.wt.tour.model.Hotel;
import org.labs.wt.tour.model.Tour;

import java.text.SimpleDateFormat;
import java.util.List;


public class TourCommand extends AbstractCommand {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public TourCommand() {
        super("tour");
    }


    public boolean processCommand(String[] params) {
        if (params.length < 2) {
            System.out.println("invalid tour params");
            return true;
        }

        if (super.processCommand(params)) {
            return true;
        }

        switch (params[1]) {
            case "add":
                addTour(params);
                break;

            case "update":
                updateTour(params);
                break;

            case "delete":
                deleteTour(params);
                break;

            case "getall":
                getAllTours(params);
                break;

            case "getbyid":
                getByIdTour(params);
                break;

            case "convert":
                convertTour(params);
                break;

            default:
                System.out.println("unknown tour command");
                return false;
        }

        return true;
    }

    protected void printHelp() {
        super.printHelp();
        System.out.println("  tour add <id> <clientID> <hotelID> <fromDate> <toDate> <persons>");
        System.out.println("  tour update <id> <clientID> <hotelID> <fromDate> <toDate> <persons>");
    }

    private void addTour(String[] params) {
        getTourService().addTour(getObjectFromParams(params));
    }

    private void updateTour(String[] params) {
        getTourService().updateTour(getObjectFromParams(params));
    }

    private void deleteTour(String[] params) {
        if (params.length > 2) {
            boolean result = getTourService().deleteTour(Long.parseLong(params[2]));
            if (result) {
                System.out.println("tour deleted");
            } else {
                System.out.println("tour not found");
            }
        }
    }

    private void getAllTours(String[] params) {
        List<Tour> tours = getTourService().getTours();
        for (Tour tour : tours) {
            printTour(tour);
        }
    }

    private void getByIdTour(String[] params) {
        if (params.length > 2) {
            Tour tour = getTourService().getTourById(Long.parseLong(params[2]));
            printTour(tour);
        }
    }

    private Tour getObjectFromParams(String[] params) {
        Tour tour = new Tour();

        if (params.length > 2) {
            tour.setId(Long.parseLong(params[2]));
        }
        if (params.length > 3) {
            tour.setClient(new Client());
            tour.getClient().setId(Long.parseLong(params[3]));
        }
        if (params.length > 4) {
            tour.setHotel(new Hotel());
            tour.getHotel().setId(Long.parseLong(params[4]));
        }
        if (params.length > 5) {
            try {
                tour.setFrom(dateFormat.parse(params[5]));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (params.length > 6) {
            try {
                tour.setTo(dateFormat.parse(params[6]));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (params.length > 7) {
            tour.setPersonCount(Integer.parseInt(params[7]));
        }

        return tour;
    }

    private void printTour(Tour tour) {
        if (tour != null) {
            System.out.println(
                    "id: " + tour.getId() +
                    "; clientID: " + tour.getClient().getId() +
                    "; hotelID: " + tour.getHotel().getId() +
                    "; fromDate: " + tour.getFrom().toString() +
                    "; toDate: " + tour.getTo().toString() +
                    "; persons: " + tour.getPersonCount());
        }
    }

    private TourService getTourService() {
        return getTourServicesFactory().getTourService();
    }

    private void convertTour(String[] params) {
        if (params.length < 4) {
            return;
        }

        setServiceType(params[2]);
        List<Tour> tours = getTourService().getTours();

        setServiceType(params[3]);
        for (Tour tour : tours) {
            getTourService().addTour(tour);
        }

        System.out.println("tour converted from " + params[2] + " to " + params[3] +
                "; objects: " + tours.size());
    }

}
