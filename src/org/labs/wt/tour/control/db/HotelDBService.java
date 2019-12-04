
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.HotelService;
import org.labs.wt.tour.model.*;

import java.sql.ResultSet;
import java.util.List;


public class HotelDBService extends DBService<Hotel> implements HotelService {


    public HotelDBService() {
        super("hotel");
    }

    private ResultSetListener<Hotel> resultSetListener = new ResultSetListener<Hotel>() {
        @Override
        public Hotel onResultSet(ResultSet resultSet) {
            try {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("hotel_id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotel.setRegion(new Region());
                hotel.getRegion().setId(resultSet.getInt("region_id"));
                hotel.setRank(HotelRank.valueOf(resultSet.getString("rank")));
                hotel.setMaxFoodType(FoodType.valueOf(resultSet.getString("food_type")));

                return hotel;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    };

    @Override
    public List<Hotel> getHotels() {
        return getAllObjects(resultSetListener);
    }

    @Override
    public Hotel getHotelById(long id) {
        return getObjectByID(id, "hotel_id", resultSetListener);
    }

    @Override
    public boolean addHotel(Hotel hotel) {
        String sql = "insert into hotel (hotel_id, hotel_name, region_id, rank, food_type) values (?, ?, ?, ? , ?)";
        return addObject(sql, preparedStatement -> {
            try {
                preparedStatement.setLong(1, hotel.getId());
                preparedStatement.setString(2, hotel.getHotelName());
                preparedStatement.setLong(3, hotel.getRegion().getId());
                preparedStatement.setString(4, hotel.getRank().name());
                preparedStatement.setString(5, hotel.getMaxFoodType().name());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        String sql = "update hotel set hotel_name = ?, region_id = ?, rank = ?, food_type = ? where hotel_id = ?";
        return updateObject(sql, preparedStatement -> {
            try {
                preparedStatement.setString(1, hotel.getHotelName());
                preparedStatement.setLong(2, hotel.getRegion().getId());
                preparedStatement.setString(3, hotel.getRank().name());
                preparedStatement.setString(4, hotel.getMaxFoodType().name());
                preparedStatement.setLong(5, hotel.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean deleteHotel(long id) {
        return deleteObjectByID(id, "hotel_id");
    }

    //@Override
    //public List<Hotel> filterHotels(Hotel filter) {
    //    return filterObjects(null);
    //}

}
