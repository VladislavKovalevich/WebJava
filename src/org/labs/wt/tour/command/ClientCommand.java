
package org.labs.wt.tour.command;


import org.labs.wt.tour.control.ClientService;
import org.labs.wt.tour.model.Client;

import java.util.List;


public class ClientCommand extends AbstractCommand {


    public ClientCommand() {
        super("client");
    }


    public boolean processCommand(String[] params) {
        if (params.length < 2) {
            System.out.println("invalid client params");
            return false;
        }

        if (super.processCommand(params)) {
            return true;
        }

        switch (params[1]) {
            case "add":
                addClient(params);
                break;

            case "update":
                updateClient(params);
                break;

            case "delete":
                deleteClient(params);
                break;

            case "getall":
                getAllClients(params);
                break;

            case "getbyid":
                getByIdClient(params);
                break;

            case "convert":
                convertClient(params);
                break;

            default:
                System.out.println("unknown client command");
                return false;
        }

        return true;
    }

    protected void printHelp() {
        super.printHelp();
        System.out.println("  client add <id> <name> <sirname> <address> <phone>");
        System.out.println("  client update <id> <name> <sirname> <address> <phone>");
    }

    private void addClient(String[] params) {
        getClientService().addClient(getObjectFromParams(params));
    }

    private void updateClient(String[] params) {
        getClientService().updateClient(getObjectFromParams(params));
    }

    private void deleteClient(String[] params) {
        if (params.length > 2) {
            boolean result = getClientService().deleteClient(Long.parseLong(params[2]));
            if (result) {
                System.out.println("client deleted");
            } else {
                System.out.println("client not found");
            }
        }
    }

    private void getAllClients(String[] params) {
        List<Client> clients = getClientService().getClients();
        for (Client client : clients) {
            printClient(client);
        }
    }

    private void getByIdClient(String[] params) {
        if (params.length > 2) {
            Client client = getClientService().getClientById(Long.parseLong(params[2]));
            printClient(client);
        }
    }

    private Client getObjectFromParams(String[] params) {
        Client client = new Client();

        if (params.length > 2) {
            client.setId(Long.parseLong(params[2]));
        }
        if (params.length > 3) {
            client.setName(params[3]);
        }
        if (params.length > 4) {
            client.setSirname(params[4]);
        }
        if (params.length > 5) {
            client.setAddress(params[5]);
        }
        if (params.length > 6) {
            client.setPhone(params[6]);
        }

        return client;
    }

    private void printClient(Client client) {
        if (client != null) {
            System.out.println(
                    "id: " + client.getId() +
                    "; name: " + client.getName() +
                    "; sirname: " + client.getSirname() +
                    "; address: " + client.getAddress() +
                    "; phone: " + client.getPhone());
        }
    }

    private ClientService getClientService() {
        return getTourServicesFactory().getClientService();
    }

    private void convertClient(String[] params) {
        if (params.length < 4) {
            return;
        }

        setServiceType(params[2]);
        List<Client> clients = getClientService().getClients();

        setServiceType(params[3]);
        for (Client client : clients) {
            getClientService().addClient(client);
        }

        System.out.println("client converted from " + params[2] + " to " + params[3] +
                "; objects: " + clients.size());
    }

}
