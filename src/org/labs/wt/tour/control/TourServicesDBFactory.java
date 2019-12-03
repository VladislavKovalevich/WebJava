
package org.labs.wt.tour.control;


import org.labs.wt.tour.control.db.*;


public class TourServicesDBFactory implements TourServicesFactory {

    private static final TourServicesDBFactory instance = new TourServicesDBFactory();

    private CountryService countryService = null;
    private RegionService regionService = null;
    private ClientService clientService = null;
    private HotelService hotelService = null;
    private TransportService transportService = null;
    private TourService tourService = null;


    static final TourServicesDBFactory getInstance() {
        return instance;
    }

    private TourServicesDBFactory() {
    }

    @Override
    public CountryService getCountryService() {
        if (countryService == null) {
            countryService = new CountryDBService();
        }

        return countryService;
    }

    @Override
    public RegionService getRegionService() {
        if (regionService == null) {
            regionService = new RegionDBService();
        }

        return regionService;
    }

    @Override
    public ClientService getClientService() {
        if (clientService == null) {
            clientService = new ClientDBService();
        }

        return clientService;
    }

    @Override
    public HotelService getHotelService() {
        if (hotelService == null) {
            hotelService = new HotelDBService();
        }

        return hotelService;
    }

    @Override
    public TransportService getTransportService() {
        if (transportService == null) {
            transportService = new TransportDBService();
        }

        return transportService;
    }

    @Override
    public TourService getTourService() {
        if (tourService == null) {
            tourService = new TourDBService();
        }

        return tourService;
    }

}
