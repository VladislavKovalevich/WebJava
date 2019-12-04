
package org.labs.wt.tour.command;


import org.labs.wt.tour.control.HotelService;
import org.labs.wt.tour.model.FoodType;
import org.labs.wt.tour.model.Hotel;
import org.labs.wt.tour.model.HotelRank;
import org.labs.wt.tour.model.Region;

import java.util.List;


public class HotelCommand extends AbstractCommand {


    public HotelCommand() {
        super("hotel");
    }


    public boolean processCommand(String[] params) {
        if (params.length < 2) {
            System.out.println("invalid hotel params");
            return false;
        }

        if (super.processCommand(params)) {
            return true;
        }

        switch (params[1]) {
            case "add":
                addHotel(params);
                break;

            case "update":
                updateHotel(params);
                break;

            case "delete":
                deleteHotel(params);
                break;

            case "getall":
                getAllHotels(params);
                break;

            case "getbyid":
                getByIdHotel(params);
                break;

            case "convert":
                convertHotel(params);
                break;

            default:
                System.out.println("unknown hotel command");
                return false;
        }

        return true;
    }

    protected void printHelp() {
        super.printHelp();
        System.out.println("  hotel add <id> <name> <regionID> <rank> <foodType>");
        System.out.println("  hotel update <id> <name> <regionID> <rank> <foodType>");
    }

    private void addHotel(String[] params) {
        getHotelService().addHotel(getObjectFromParams(params));
    }

    private void updateHotel(String[] params) {
        getHotelService().updateHotel(getObjectFromParams(params));
    }

    private void deleteHotel(String[] params) {
        if (params.length > 2) {
            boolean result = getHotelService().deleteHotel(Long.parseLong(params[2]));
            if (result) {
                System.out.println("hotel deleted");
            } else {
                System.out.println("hotel not found");
            }
        }
    }

    private void getAllHotels(String[] params) {
        List<Hotel> hotels = getHotelService().getHotels();
        for (Hotel hotel : hotels) {
            printHotel(hotel);
        }
    }

    private void getByIdHotel(String[] params) {
        if (params.length > 2) {
            Hotel hotel = getHotelService().getHotelById(Long.parseLong(params[2]));
            printHotel(hotel);
        }
    }

    private Hotel getObjectFromParams(String[] params) {
        Hotel hotel = new Hotel();

        if (params.length > 2) {
            hotel.setId(Long.parseLong(params[2]));
        }
        if (params.length > 3) {
            hotel.setHotelName(params[3]);
        }
        if (params.length > 4) {
            hotel.setRegion(new Region());
            hotel.getRegion().setId(Long.parseLong(params[4]));
        }
        if (params.length > 5) {
            hotel.setRank(HotelRank.valueOf(params[5]));
        }
        if (params.length > 6) {
            hotel.setMaxFoodType(FoodType.valueOf(params[6]));
        }

        return hotel;
    }

    private void printHotel(Hotel hotel) {
        if (hotel != null) {
            System.out.println(
                    "id: " + hotel.getId() +
                            "; name: " + hotel.getHotelName() +
                            "; regionID: " + hotel.getRegion().getId() +
                            "; rank: " + hotel.getRank().name() +
                            "; foodType: " + hotel.getMaxFoodType().name());
        }
    }

    private HotelService getHotelService() {
        return getTourServicesFactory().getHotelService();
    }

    private void convertHotel(String[] params) {
        if (params.length < 4) {
            return;
        }

        setServiceType(params[2]);
        List<Hotel> hotels = getHotelService().getHotels();

        setServiceType(params[3]);
        for (Hotel hotel : hotels) {
            getHotelService().addHotel(hotel);
        }

        System.out.println("hotel converted from " + params[2] + " to " + params[3] +
                "; objects: " + hotels.size());
    }

}
