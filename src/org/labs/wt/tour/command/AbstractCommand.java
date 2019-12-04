
package org.labs.wt.tour.command;


import org.labs.wt.tour.control.TourServices;
import org.labs.wt.tour.control.TourServicesFactory;

abstract class AbstractCommand {

    private ServiceType serviceType = ServiceType.FILE_TYPE;

    private String commandService;


    protected AbstractCommand(String commandService) {
        this.commandService = commandService;
    }


    public boolean processCommand(String[] params) {
        if (params.length < 2) {
            System.out.println("invalid client params");
            return true;
        }

        switch (params[1]) {
            case "help":
                printHelp();
                return true;

            case "type":
                if (params.length > 2) {
                    setServiceType(params[2]);
                    return true;
                } else {
                    return false;
                }
        }

        return false;
    }

    protected void setServiceType(String type) {
        if (type.equals("file")) {
            serviceType = ServiceType.FILE_TYPE;
        } else if (type.equals("xml")) {
            serviceType = ServiceType.XML_TYPE;
        } else if (type.equals("db")) {
            serviceType = ServiceType.DB_TYPE;
        } else {
            System.out.println("unknown service type");
        }
    }

    protected ServiceType getServiceType() {
        return serviceType;
    }

    protected void printHelp() {
        System.out.println("  " + commandService + " help");
        System.out.println("  " + commandService + " type file|xml|db");
        System.out.println("  " + commandService + " delete <id>");
        System.out.println("  " + commandService + " getall");
        System.out.println("  " + commandService + " getbyid <id>");
    }

    protected TourServicesFactory getTourServicesFactory() {
        return serviceType == ServiceType.FILE_TYPE ?
                TourServices.getFileServices() :
                serviceType == ServiceType.XML_TYPE ?
                        TourServices.getXmlServices() :
                        TourServices.getDBServices();
    }

}
