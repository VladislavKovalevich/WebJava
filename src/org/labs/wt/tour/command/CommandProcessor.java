
package org.labs.wt.tour.command;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommandProcessor {

    private static final Logger LOGGER = LogManager.getLogger(CommandProcessor.class);

    private static final CommandProcessor instance = new CommandProcessor();

    public static final CommandProcessor getInstance() {
        return instance;
    }


    private CountryCommand countryCommand = null;
    private RegionCommand regionCommand = null;
    private ClientCommand clientCommand = null;
    private HotelCommand hotelCommand = null;
    private TransportCommand transportCommand = null;
    private TourCommand tourCommand = null;


    private CommandProcessor() {
    }


    public void processCommand(String line) {
        if ((line == null) || (line.isEmpty())) {
            return;
        }

        LOGGER.debug("executing command: {}", line);

        String[] params = line.split(" ");

        switch (params[0]) {
            case "help":
                printHelp();
                break;

            case "country":
                getCountryCommand().processCommand(params);
                break;

            case "region":
                getRegionCommand().processCommand(params);
                break;

            case "client":
                getClientCommand().processCommand(params);
                break;

            case "hotel":
                getHotelCommand().processCommand(params);
                break;

            case "transport":
                getTransportCommand().processCommand(params);
                break;

            case "tour":
                getTourCommand().processCommand(params);
                break;

            default:
                System.out.println("unknown command");
        }
    }

    private void printHelp() {
        System.out.println("country help");
        System.out.println("region help");
        System.out.println("client help");
        System.out.println("hotel help");
        System.out.println("transport help");
        System.out.println("tour help");
    }

    private CountryCommand getCountryCommand() {
        if (countryCommand == null) {
            countryCommand = new CountryCommand();
        }
        return countryCommand;
    }

    private RegionCommand getRegionCommand() {
        if (regionCommand == null) {
            regionCommand = new RegionCommand();
        }
        return regionCommand;
    }

    private ClientCommand getClientCommand() {
        if (clientCommand == null) {
            clientCommand = new ClientCommand();
        }
        return clientCommand;
    }

    private HotelCommand getHotelCommand() {
        if (hotelCommand == null) {
            hotelCommand = new HotelCommand();
        }
        return hotelCommand;
    }

    private TransportCommand getTransportCommand() {
        if (transportCommand == null) {
            transportCommand = new TransportCommand();
        }
        return transportCommand;
    }

    private TourCommand getTourCommand() {
        if (tourCommand == null) {
            tourCommand = new TourCommand();
        }
        return tourCommand;
    }

}
