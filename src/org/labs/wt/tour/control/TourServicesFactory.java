
package org.labs.wt.tour.control;


public interface TourServicesFactory {

    CountryService getCountryService();

    RegionService getRegionService();

    ClientService getClientService();

    HotelService getHotelService();

    TransportService getTransportService();

    TourService getTourService();

}
