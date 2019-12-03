
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.TourService;
import org.labs.wt.tour.model.Client;
import org.labs.wt.tour.model.Hotel;
import org.labs.wt.tour.model.Tour;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.sql.Date;
import java.util.List;


public class TourXmlService extends XmlService<Tour> implements TourService {

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

        tour.setId(Long.parseLong(element.getAttribute("id")));
        tour.getClient().setId(Long.parseLong(element.getAttribute("clientID")));
        tour.getHotel().setId(Long.parseLong(element.getAttribute("hotelID")));
        tour.setFrom(Date.valueOf(element.getAttribute("fromDate")));
        tour.setTo(Date.valueOf(element.getAttribute("toDate")));
        tour.setPersonCount(Integer.parseInt(element.getAttribute("persons")));

        return tour;
    }

    @Override
    protected Node convertToNode(Tour object, Element element) {
        if ((object == null) || (element == null)) {
            return element;
        }

        element.setAttribute("id", Long.toString(object.getId()));
        element.setAttribute("clientID", Long.toString(object.getClient().getId()));
        element.setAttribute("hotelID", Long.toString(object.getHotel().getId()));
        element.setAttribute("fromDate", object.getFrom().toString());
        element.setAttribute("toDate", object.getTo().toString());
        element.setAttribute("persons", object.getPersonCount().toString());

        return element;
    }

    @Override
    protected String getElementName() {
        return "tour";
    }

}
