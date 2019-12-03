
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.ClientService;
import org.labs.wt.tour.model.Client;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;


public class ClientXmlService extends XmlService<Client> implements ClientService {

    public ClientXmlService(String file) {
        super("clients", file);
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
    protected Client convertToObject(Node node) {
        if ((node == null) || (node.getNodeType() != Node.ELEMENT_NODE)) {
            return null;
        }

        Element element = (Element)node;
        Client client = new Client();
        client.setId(Long.parseLong(element.getAttribute("id")));
        client.setName(element.getAttribute("name"));
        client.setSirname(element.getAttribute("sirname"));
        client.setAddress(element.getAttribute("address"));
        client.setPhone(element.getAttribute("phone"));

        return client;
    }

    @Override
    protected Node convertToNode(Client object, Element element) {
        if ((object == null) || (element == null)) {
            return element;
        }

        element.setAttribute("id", Long.toString(object.getId()));
        element.setAttribute("name", object.getName());
        element.setAttribute("sirname", object.getSirname());
        element.setAttribute("address", object.getAddress());
        element.setAttribute("phone", object.getPhone());

        return element;
    }

    @Override
    protected String getElementName() {
        return "client";
    }

}
