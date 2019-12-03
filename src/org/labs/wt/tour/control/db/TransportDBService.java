
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.TransportService;
import org.labs.wt.tour.model.Transport;

import java.util.List;


public class TransportDBService extends DBService<Transport> implements TransportService {


    public TransportDBService() {
        super("transport");
    }

    @Override
    public List<Transport> getTransports() {
        return getAllObjects();
    }

    @Override
    public Transport getTransportById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addTransport(Transport transport) {
        return addObject(transport);
    }

    @Override
    public boolean updateTransport(Transport transport) {
        return updateObject(transport);
    }

    @Override
    public boolean deleteTransport(long id) {
        return deleteObjectByID(id);
    }

}
