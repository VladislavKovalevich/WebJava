
package org.labs.wt.tour.control;


import org.labs.wt.tour.model.Hotel;

import java.util.List;


public interface HotelService {

    List<Hotel> getHotels();

    Hotel getHotelById(long id);

    boolean addHotel(Hotel hotel);

    boolean updateHotel(Hotel hotel);

    boolean deleteHotel(long id);

}
