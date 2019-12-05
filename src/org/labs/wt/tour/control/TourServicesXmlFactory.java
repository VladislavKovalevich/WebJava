
package org.labs.wt.tour.control;


import org.labs.wt.tour.control.xml.*;

import java.io.File;


public class TourServicesXmlFactory implements TourServicesFactory {

    private static final TourServicesXmlFactory instance = new TourServicesXmlFactory("xml");

    private final String dir;

    private CountryService countryService = null;
    private RegionService regionService = null;
    private ClientService clientService = null;
    private HotelService hotelService = null;
    private TransportService transportService = null;
    private TourService tourService = null;


    static final TourServicesXmlFactory getInstance() {
        return instance;
    }

    private TourServicesXmlFactory(final String dir) {
        this.dir = "./data" + File.separator + dir;
    }

    @Override
    public CountryService getCountryService() {
        if (countryService == null) {
            countryService = new CountryXmlService(dir + File.separator + "country.xml");
        }

        return countryService;
    }

    @Override
    public RegionService getRegionService() {
        if (regionService == null) {
            regionService = new RegionXmlService(dir + File.separator + "region.xml");
        }

        return regionService;
    }

    @Override
    public ClientService getClientService() {
        if (clientService == null) {
            clientService = new ClientXmlService(dir + File.separator + "client.xml");
        }

        return clientService;
    }

    @Override
    public HotelService getHotelService() {
        if (hotelService == null) {
            hotelService = new HotelXmlService(dir + File.separator + "hotel.xml");
        }

        return hotelService;
    }

    @Override
    public TransportService getTransportService() {
        if (transportService == null) {
            transportService = new TransportXmlService(dir + File.separator + "transport.xml");
        }

        return transportService;
    }

    @Override
    public TourService getTourService() {
        if (tourService == null) {
            tourService = new TourXmlService(dir + File.separator + "tour.xml");
        }

        return tourService;
    }

}
