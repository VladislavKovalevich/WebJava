
package org.labs.wt.tour.control.file;


import org.labs.wt.tour.control.ClientService;
import org.labs.wt.tour.model.Client;

import java.util.List;


public class ClientFileService extends FileService<Client> implements ClientService {

    public ClientFileService(String file) {
        super(file);
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

    @Override
    protected Client convertToObject(String line) {
        if ((line != null) && (line.trim().length() > 0)) {
            String[] parts = line.split("\\|");

            Client client = new Client();
            client.setId(Long.parseLong(parts[0]));
            client.setName(parts[1]);
            client.setSirname(parts[2]);
            client.setAddress(parts[3]);
            client.setPhone(parts[4]);

            return client;
        }

        return null;
    }

    @Override
    protected String convertToString(Client object) {
        StringBuffer buffer = new StringBuffer().
                append(object.getId()).append("|").
                append(object.getName()).append("|").
                append(object.getSirname()).append("|").
                append(object.getAddress()).append("|").
                append(object.getPhone());

        return buffer.toString();
    }

}
