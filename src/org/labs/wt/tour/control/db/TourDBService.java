
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.TourService;
import org.labs.wt.tour.model.*;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;


public class TourDBService extends DBService<Tour> implements TourService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    public TourDBService() {
        super("tour");
    }

    private ResultSetListener<Tour> resultSetListener = new ResultSetListener<Tour>() {
        @Override
        public Tour onResultSet(ResultSet resultSet) {
            try {
                Tour tour = new Tour();
                tour.setId(resultSet.getInt("tour_id"));
                tour.setClient(new Client());
                tour.getClient().setId(resultSet.getInt("client_id"));
                tour.setHotel(new Hotel());
                tour.getHotel().setId(resultSet.getInt("hotel_id"));
                tour.setFrom(dateFormat.parse(resultSet.getString("date_from")));
                tour.setTo(dateFormat.parse(resultSet.getString("date_to")));
                tour.setPersonCount(resultSet.getInt("persons"));

                return tour;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    };

    @Override
    public List<Tour> getTours() {
        return getAllObjects(resultSetListener);
    }

    @Override
    public Tour getTourById(long id) {
        return getObjectByID(id, "tour_id", resultSetListener);
    }

    @Override
    public boolean addTour(Tour tour) {
        String sql = "insert into tour (tour_id, client_id, hotel_id, date_from, date_to, persons) values (?, ?, ?, ?, ?, ?)";
        return addObject(sql, preparedStatement -> {
            try {
                preparedStatement.setLong(1, tour.getId());
                preparedStatement.setLong(2, tour.getClient().getId());
                preparedStatement.setLong(3, tour.getHotel().getId());
                preparedStatement.setString(4, dateFormat.format(tour.getFrom()));
                preparedStatement.setString(5, dateFormat.format(tour.getTo()));
                preparedStatement.setLong(6, tour.getPersonCount());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean updateTour(Tour tour) {
        String sql = "update tour set client_id = ?, hotel_id = ?, date_from = ?, date_to = ?, persons = ? where tour_id = ?";
        return updateObject(sql, preparedStatement -> {
            try {
                preparedStatement.setLong(1, tour.getClient().getId());
                preparedStatement.setLong(2, tour.getHotel().getId());
                preparedStatement.setString(3, dateFormat.format(tour.getFrom()));
                preparedStatement.setString(4, dateFormat.format(tour.getTo()));
                preparedStatement.setLong(5, tour.getPersonCount());
                preparedStatement.setLong(6, tour.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean deleteTour(long id) {
        return deleteObjectByID(id, "tour_id");
    }

}
