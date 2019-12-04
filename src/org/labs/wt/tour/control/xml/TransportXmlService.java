
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.TransportService;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Tour;
import org.labs.wt.tour.model.Transport;
import org.labs.wt.tour.model.TransportType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;


public class TransportXmlService extends XmlService<Transport> implements TransportService {

    public TransportXmlService(String file) {
        super("transports", file);
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
    protected Transport convertToObject(Node node) {
        if ((node == null) || (node.getNodeType() != Node.ELEMENT_NODE)) {
            return null;
        }

        Element element = (Element)node;

        Transport transport = new Transport();
        transport.setTour(new Tour());
        transport.setFrom(new Country());
        transport.setTo(new Country());

        transport.setId(Long.parseLong(element.getAttribute("id").substring(2)));
        transport.getTour().setId(Long.parseLong(element.getAttribute("tourID")));
        transport.setType(TransportType.valueOf(element.getAttribute("type")));
        transport.getFrom().setId(Long.parseLong(element.getAttribute("fromID")));
        transport.getTo().setId(Long.parseLong(element.getAttribute("toID")));

        return transport;
    }

    @Override
    protected Node convertToNode(Transport object, Element element) {
        if ((object == null) || (element == null)) {
            return element;
        }

        element.setAttribute("id", "id" + object.getId());
        element.setAttribute("tourID", Long.toString(object.getTour().getId()));
        element.setAttribute("type", object.getType().name());
        element.setAttribute("fromID", Long.toString(object.getFrom().getId()));
        element.setAttribute("toID", Long.toString(object.getTo().getId()));

        return element;
    }

    @Override
    protected String getElementName() {
        return "transport";
    }

}
