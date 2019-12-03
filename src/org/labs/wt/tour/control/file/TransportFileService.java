
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.TransportService;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Tour;
import org.labs.wt.tour.model.Transport;
import org.labs.wt.tour.model.TransportType;

import java.util.List;


public class TransportFileService extends FileService<Transport> implements TransportService {

    public TransportFileService(String file) {
        super(file);
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

    @Override
    protected Transport convertToObject(String line) {
        if ((line != null) && (line.trim().length() > 0)) {
            String[] parts = line.split("\\|");

            Transport transport = new Transport();
            transport.setTour(new Tour());
            transport.setFrom(new Country());
            transport.setTo(new Country());

            transport.setId(Long.parseLong(parts[0]));
            transport.getTour().setId(Long.parseLong(parts[1]));
            transport.setType(TransportType.valueOf(parts[2]));
            transport.getFrom().setId(Long.parseLong(parts[3]));
            transport.getTo().setId(Long.parseLong(parts[4]));

            return transport;
        }

        return null;
    }

    @Override
    protected String convertToString(Transport object) {
        StringBuffer buffer = new StringBuffer().
                append(object.getId()).append("|").
                append(object.getTour().getId()).append("|").
                append(object.getType().name()).append("|").
                append(object.getFrom().getId()).append("|").
                append(object.getTo().getId());

        return buffer.toString();
    }

}
