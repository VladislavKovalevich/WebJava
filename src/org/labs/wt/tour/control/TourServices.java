
package org.labs.wt.tour.control;


public class TourServices {

    public static TourServicesFactory getFileServices() {
        return TourServicesFileFactory.getInstance();
    }

    public static TourServicesFactory getXmlServices() {
        return TourServicesXmlFactory.getInstance();
    }

}
