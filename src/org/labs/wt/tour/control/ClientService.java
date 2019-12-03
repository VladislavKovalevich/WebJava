
package org.labs.wt.tour.control;


import org.labs.wt.tour.model.Client;

import java.util.List;


public interface ClientService {

    List<Client> getClients();

    Client getClientById(long id);

    boolean addClient(Client client);

    boolean updateClient(Client client);

    boolean deleteClient(long id);

}
