
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.HotelService;
import org.labs.wt.tour.model.Hotel;

import java.util.List;


public class HotelDBService extends DBService<Hotel> implements HotelService {


    public HotelDBService() {
        super("hotel");
    }

    @Override
    public List<Hotel> getHotels() {
        return getAllObjects();
    }

    @Override
    public Hotel getHotelById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addHotel(Hotel hotel) {
        return addObject(hotel);
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        return updateObject(hotel);
    }

    @Override
    public boolean deleteHotel(long id) {
        return deleteObjectByID(id);
    }

    //@Override
    //public List<Hotel> filterHotels(Hotel filter) {
    //    return filterObjects(null);
    //}

}
