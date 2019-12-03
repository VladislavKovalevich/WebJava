
package org.labs.wt.tour.control;


import org.labs.wt.tour.model.Transport;

import java.util.List;


public interface TransportService {

    List<Transport> getTransports();

    Transport getTransportById(long id);

    boolean addTransport(Transport transport);

    boolean updateTransport(Transport transport);

    boolean deleteTransport(long id);

}
