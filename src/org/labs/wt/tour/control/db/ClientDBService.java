
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.ClientService;
import org.labs.wt.tour.model.Client;

import java.util.List;


public class ClientDBService extends DBService<Client> implements ClientService {


    public ClientDBService() {
        super("client");
    }

    @Override
    public List<Client> getClients() {
        return getAllObjects();
    }

    @Override
    public Client getClientById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addClient(Client client) {
        return addObject(client);
    }

    @Override
    public boolean updateClient(Client client) {
        return updateObject(client);
    }

    @Override
    public boolean deleteClient(long id) {
        return deleteObjectByID(id);
    }

}
