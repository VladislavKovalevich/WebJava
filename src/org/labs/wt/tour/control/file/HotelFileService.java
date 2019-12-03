
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.HotelService;
import org.labs.wt.tour.model.FoodType;
import org.labs.wt.tour.model.Hotel;
import org.labs.wt.tour.model.HotelRank;
import org.labs.wt.tour.model.Region;

import java.util.List;


public class HotelFileService extends FileService<Hotel> implements HotelService {

    public HotelFileService(String file) {
        super(file);
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

    @Override
    protected String convertToString(Hotel object) {
        StringBuffer buffer = new StringBuffer().
                append(object.getId()).append("|").
                append(object.getHotelName()).append("|").
                append(object.getRegion() != null ? object.getRegion().getId() : -1).append("|").
                append(object.getRank()).append("|").
                append(object.getMaxFoodType());

        return buffer.toString();
    }

    @Override
    protected Hotel convertToObject(String line) {
        if ((line != null) && (line.trim().length() > 0)) {
            String[] parts = line.split("\\|");

            Hotel hotel = new Hotel();
            hotel.setId(Long.parseLong(parts[0]));
            hotel.setHotelName(parts[1]);

            Region region = new Region();
            region.setId(Long.parseLong(parts[2]));
            hotel.setRegion(region);

            hotel.setRank(HotelRank.valueOf(parts[3]));
            hotel.setMaxFoodType(FoodType.valueOf(parts[4]));

            return hotel;
        }

        return null;
    }

}
