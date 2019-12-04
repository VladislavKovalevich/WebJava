
package org.labs.wt.tour.command;


import org.labs.wt.tour.control.TransportService;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Tour;
import org.labs.wt.tour.model.Transport;
import org.labs.wt.tour.model.TransportType;

import java.util.List;


public class TransportCommand extends AbstractCommand {


    public TransportCommand() {
        super("transport");
    }


    public boolean processCommand(String[] params) {
        if (params.length < 2) {
            System.out.println("invalid client params");
            return false;
        }

        if (super.processCommand(params)) {
            return true;
        }

        switch (params[1]) {
            case "add":
                addTransport(params);
                break;

            case "update":
                updateTransport(params);
                break;

            case "delete":
                deleteTransport(params);
                break;

            case "getall":
                getAllTransports(params);
                break;

            case "getbyid":
                getByIdTransport(params);
                break;

            default:
                System.out.println("unknown transport command");
                return false;
        }

        return true;
    }

    protected void printHelp() {
        super.printHelp();
        System.out.println("  transport add <id> <tourID> <type> <fromID> <toID>");
        System.out.println("  transport update <id> <tourID> <type> <fromID> <toID>");
    }

    private void addTransport(String[] params) {
        getTransportService().addTransport(getObjectFromParams(params));
    }

    private void updateTransport(String[] params) {
        getTransportService().updateTransport(getObjectFromParams(params));
    }

    private void deleteTransport(String[] params) {
        if (params.length > 2) {
            boolean result = getTransportService().deleteTransport(Long.parseLong(params[2]));
            if (result) {
                System.out.println("transport deleted");
            } else {
                System.out.println("transport not found");
            }
        }
    }

    private void getAllTransports(String[] params) {
        List<Transport> transports = getTransportService().getTransports();
        for (Transport transport : transports) {
            printTransport(transport);
        }
    }

    private void getByIdTransport(String[] params) {
        if (params.length > 2) {
            Transport transport = getTransportService().getTransportById(Long.parseLong(params[2]));
            printTransport(transport);
        }
    }

    private Transport getObjectFromParams(String[] params) {
        Transport transport = new Transport();

        if (params.length > 2) {
            transport.setId(Long.parseLong(params[2]));
        }
        if (params.length > 3) {
            transport.setTour(new Tour());
            transport.getTour().setId(Long.parseLong(params[3]));
        }
        if (params.length > 4) {
            transport.setType(TransportType.valueOf(params[4]));
        }
        if (params.length > 5) {
            transport.setFrom(new Country());
            transport.getFrom().setId(Long.parseLong(params[5]));
        }
        if (params.length > 6) {
            transport.setTo(new Country());
            transport.getTo().setId(Long.parseLong(params[6]));
        }

        return transport;
    }

    private void printTransport(Transport transport) {
        if (transport != null) {
            System.out.println("id: " + transport.getId() +
                    "; tourID: " + transport.getTour().getId() +
                    "; type: " + transport.getType().name() +
                    "; fromID: " + transport.getFrom().getId() +
                    "; toID: " + transport.getTo().getId());
        }
    }

    private TransportService getTransportService() {
        return getTourServicesFactory().getTransportService();
    }

    private void convertTransport(String[] params) {
        if (params.length < 4) {
            return;
        }

        setServiceType(params[2]);
        List<Transport> transports = getTransportService().getTransports();

        setServiceType(params[3]);
        for (Transport transport : transports) {
            getTransportService().addTransport(transport);
        }

        System.out.println("transport converted from " + params[2] + " to " + params[3] +
                "; objects: " + transports.size());
    }

}
