
package org.labs.wt.tour.control;


import org.labs.wt.tour.control.file.*;

import java.io.File;


public class TourServicesFileFactory implements TourServicesFactory {

    private static final TourServicesFileFactory instance = new TourServicesFileFactory("file");

    private final String dir;

    private CountryService countryService = null;
    private RegionService regionService = null;
    private ClientService clientService = null;
    private HotelService hotelService = null;
    private TransportService transportService = null;
    private TourService tourService = null;


    static final TourServicesFileFactory getInstance() {
        return instance;
    }

    private TourServicesFileFactory(final String dir) {
        this.dir = "./data" + File.separator + dir;
    }

    @Override
    public CountryService getCountryService() {
        if (countryService == null) {
            countryService = new CountryFileService(dir + File.separator + "country.txt");
        }

        return countryService;
    }

    @Override
    public RegionService getRegionService() {
        if (regionService == null) {
            regionService = new RegionFileService(dir + File.separator + "region.txt");
        }

        return regionService;
    }

    @Override
    public ClientService getClientService() {
        if (clientService == null) {
            clientService = new ClientFileService(dir + File.separator + "client.txt");
        }

        return clientService;
    }

    @Override
    public HotelService getHotelService() {
        if (hotelService == null) {
            hotelService = new HotelFileService(dir + File.separator + "hotel.txt");
        }

        return hotelService;
    }

    @Override
    public TransportService getTransportService() {
        if (transportService == null) {
            transportService = new TransportFileService(dir + File.separator + "transport.txt");
        }

        return transportService;
    }

    @Override
    public TourService getTourService() {
        if (tourService == null) {
            tourService = new TourFileService(dir + File.separator + "tour.txt");
        }

        return tourService;
    }

}
