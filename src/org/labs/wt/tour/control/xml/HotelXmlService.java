
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.HotelService;
import org.labs.wt.tour.model.FoodType;
import org.labs.wt.tour.model.Hotel;
import org.labs.wt.tour.model.HotelRank;
import org.labs.wt.tour.model.Region;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;


public class HotelXmlService extends XmlService<Hotel> implements HotelService {

    public HotelXmlService(String file) {
        super("hotels", file);
    }

    @Override
    public List<Hotel> getHotels() {
        return getAllObjects();
    }

    @Override
    public Hotel getHotelById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addHotel(Hotel hotel) {
        return addObject(hotel);
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        return updateObject(hotel);
    }

    @Override
    public boolean deleteHotel(long id) {
        return deleteObjectByID(id);
    }

    @Override
    protected Node convertToNode(Hotel object, Element element) {
        if ((object == null) || (element == null)) {
            return element;
        }

        element.setAttribute("id", Long.toString(object.getId()));
        element.setAttribute("name", object.getHotelName());
        element.setAttribute("regionID", Long.toString(object.getRegion() != null ? object.getRegion().getId() : -1));
        element.setAttribute("rank", object.getRank().name());
        element.setAttribute("food", object.getMaxFoodType().name());

        return element;
    }

    @Override
    protected Hotel convertToObject(Node node) {
        if ((node == null) || (node.getNodeType() != Node.ELEMENT_NODE)) {
            return null;
        }

        Element element = (Element)node;
        Hotel hotel = new Hotel();
        hotel.setId(Long.parseLong(element.getAttribute("id")));
        hotel.setHotelName(element.getAttribute("name"));

        Region region = new Region();
        region.setId(Long.parseLong(element.getAttribute("regionID")));
        hotel.setRegion(region);

        hotel.setRank(HotelRank.valueOf(element.getAttribute("rank")));
        hotel.setMaxFoodType(FoodType.valueOf(element.getAttribute("food")));

        return hotel;
    }

    @Override
    protected String getElementName() {
        return "hotel";
    }

}
