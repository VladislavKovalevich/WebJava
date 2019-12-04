
package org.labs.wt.tour.control.db;


import org.labs.wt.tour.control.ClientService;
import org.labs.wt.tour.model.*;

import java.sql.ResultSet;
import java.util.List;


public class ClientDBService extends DBService<Client> implements ClientService {


    public ClientDBService() {
        super("client");
    }

    private ResultSetListener<Client> resultSetListener = new ResultSetListener<Client>() {
        @Override
        public Client onResultSet(ResultSet resultSet) {
            try {
                Client client = new Client();
                client.setId(resultSet.getInt("client_id"));
                client.setName(resultSet.getString("client_name"));
                client.setSirname(resultSet.getString("client_sirname"));
                client.setAddress(resultSet.getString("address"));
                client.setPhone(resultSet.getString("phone"));

                return client;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    };

    @Override
    public List<Client> getClients() {
        return getAllObjects(resultSetListener);
    }

    @Override
    public Client getClientById(long id) {
        return getObjectByID(id, "client_id", resultSetListener);
    }

    @Override
    public boolean addClient(Client client) {
        String sql = "insert into client (client_id, client_name, client_sirname, address, phone) values (?, ?, ?, ?, ?)";
        return addObject(sql, preparedStatement -> {
            try {
                preparedStatement.setLong(1, client.getId());
                preparedStatement.setString(2, client.getName());
                preparedStatement.setString(3, client.getSirname());
                preparedStatement.setString(4, client.getAddress());
                preparedStatement.setString(5, client.getPhone());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean updateClient(Client client) {
        String sql = "update client set client_name = ?, client_sirname = ?, address = ?, phone = ? where client = ?";
        return updateObject(sql, preparedStatement -> {
            try {
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getSirname());
                preparedStatement.setString(3, client.getAddress());
                preparedStatement.setString(4, client.getPhone());
                preparedStatement.setLong(5, client.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public boolean deleteClient(long id) {
        return deleteObjectByID(id, "client_id");
    }

}
