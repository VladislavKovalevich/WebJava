
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.TourService;
import org.labs.wt.tour.model.Client;
import org.labs.wt.tour.model.Hotel;
import org.labs.wt.tour.model.Tour;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.text.SimpleDateFormat;
import java.util.List;


public class TourXmlService extends XmlService<Tour> implements TourService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public TourXmlService(String file) {
        super("tours", file);
    }

    @Override
    public List<Tour> getTours() {
        return getAllObjects();
    }

    @Override
    public Tour getTourById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addTour(Tour tour) {
        return addObject(tour);
    }

    @Override
    public boolean updateTour(Tour tour) {
        return updateObject(tour);
    }

    @Override
    public boolean deleteTour(long id) {
        return deleteObjectByID(id);
    }

    @Override
    protected Tour convertToObject(Node node) {
        if ((node == null) || (node.getNodeType() != Node.ELEMENT_NODE)) {
            return null;
        }

        Element element = (Element)node;

        Tour tour = new Tour();
        tour.setClient(new Client());
        tour.setHotel(new Hotel());

        tour.setId(Long.parseLong(element.getAttribute("id").substring(2)));
        tour.getClient().setId(Long.parseLong(element.getAttribute("clientID")));
        tour.getHotel().setId(Long.parseLong(element.getAttribute("hotelID")));
        try {
            tour.setFrom(dateFormat.parse(element.getAttribute("fromDate")));
            tour.setTo(dateFormat.parse(element.getAttribute("toDate")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tour.setPersonCount(Integer.parseInt(element.getAttribute("persons")));

        return tour;
    }

    @Override
    protected Node convertToNode(Tour object, Element element) {
        if ((object == null) || (element == null)) {
            return element;
        }

        element.setAttribute("id", "id" + object.getId());
        element.setAttribute("clientID", Long.toString(object.getClient().getId()));
        element.setAttribute("hotelID", Long.toString(object.getHotel().getId()));
        element.setAttribute("fromDate", dateFormat.format(object.getFrom()));
        element.setAttribute("toDate", dateFormat.format(object.getTo()));
        element.setAttribute("persons", object.getPersonCount().toString());

        return element;
    }

    @Override
    protected String getElementName() {
        return "tour";
    }

}
