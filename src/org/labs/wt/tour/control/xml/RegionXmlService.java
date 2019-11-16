
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.RegionService;
import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Region;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;


public class RegionXmlService extends XmlService<Region> implements RegionService {

    public RegionXmlService(final String file) {
        super("regions", file);
    }

    @Override
    public List<Region> getRegions() {
        return getAllObjects();
    }

    @Override
    public Region getRegionById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addRegion(Region region) {
        return addObject(region);
    }

    @Override
    public boolean updateRegion(Region region) {
        return updateObject(region);
    }

    @Override
    public boolean deleteRegion(long id) {
        return deleteObjectByID(id);
    }

    @Override
    protected String getElementName() {
        return "region";
    }

    @Override
    protected Node convertToNode(Region object, Element element) {
        if ((object == null) || (element == null)) {
            return element;
        }

        element.setAttribute("id", Long.toString(object.getId()));
        element.setAttribute("name", object.getRegionName());
        element.setAttribute("countryID", Long.toString(object.getCountry() != null ? object.getCountry().getId() : -1));

        return element;
    }

    @Override
    protected Region convertToObject(Node node) {
        if ((node == null) || (node.getNodeType() != Node.ELEMENT_NODE)) {
            return null;
        }

        Element element = (Element)node;
        Region region = new Region();
        region.setId(Long.parseLong(element.getAttribute("id")));
        region.setRegionName(element.getAttribute("name"));

        region.setCountry(new Country());
        region.getCountry().setId(Long.parseLong(element.getAttribute("countryID")));

        return region;
    }

}
