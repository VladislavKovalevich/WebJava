
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.TransportService;
import org.labs.wt.tour.model.*;

import java.sql.ResultSet;
import java.util.List;


public class TransportDBService extends DBService<Transport> implements TransportService {


    public TransportDBService() {
        super("transport");
    }

    private ResultSetListener<Transport> resultSetListener = new ResultSetListener<Transport>() {
        @Override
        public Transport onResultSet(ResultSet resultSet) {
            try {
                Transport transport = new Transport();
                transport.setId(resultSet.getInt("transport_id"));
                transport.setTour(new Tour());
                transport.getTour().setId(resultSet.getInt("tour_id"));
                transport.setType(TransportType.valueOf(resultSet.getString("type")));
                transport.setFrom(new Country());
                transport.getFrom().setId(resultSet.getInt("country_from_id"));
                transport.setTo(new Country());
                transport.getTo().setId(resultSet.getInt("country_to_id"));

                return transport;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    };

    @Override
    public List<Transport> getTransports() {
        return getAllObjects(resultSetListener);
    }

    @Override
    public Transport getTransportById(long id) {
        return getObjectByID(id, "transport_id", resultSetListener);
    }

    @Override
    public boolean addTransport(Transport transport) {
        String sql = "insert into transport (transport_id, tour_id, type, country_from_id, country_to_id) values (?, ?, ?, ?, ?)";
        return addObject(sql, preparedStatement -> {
            try {
                preparedStatement.setLong(1, transport.getId());
                preparedStatement.setLong(2, transport.getTour().getId());
                preparedStatement.setString(3, transport.getType().name());
                preparedStatement.setLong(4, transport.getFrom().getId());
                preparedStatement.setLong(5, transport.getTo().getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean updateTransport(Transport transport) {
        String sql = "update transport set tour_id = ?, type = ?, country_from_id = ?, country_to_id = ? where transport_id = ?";
        return updateObject(sql, preparedStatement -> {
            try {
                preparedStatement.setLong(1, transport.getTour().getId());
                preparedStatement.setString(2, transport.getType().name());
                preparedStatement.setLong(3, transport.getFrom().getId());
                preparedStatement.setLong(4, transport.getTo().getId());
                preparedStatement.setLong(5, transport.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean deleteTransport(long id) {
        return deleteObjectByID(id, "transport_id");
    }

}
