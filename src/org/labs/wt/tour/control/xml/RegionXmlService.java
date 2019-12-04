
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.FilterListener;
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
    public List<Region> filterRegions(Region filter) {
        return filterObjects(
                (FilterListener<Region>) region -> {
                    boolean res1 = true;
                    if ((region.getRegionName() != null) &&
                            (filter.getRegionName() != null) &&
                            (!filter.getRegionName().isEmpty()) &&
                            (!region.getRegionName().contains(filter.getRegionName()))) {
                        res1 = false;
                    }

                    boolean res2 = true;
                    if ((region.getCountry() != null) &&
                            (filter.getCountry() != null) &&
                            (region.getCountry().getId() != filter.getCountry().getId())) {
                        res2 = false;
                    }

                    return res1 & res2;
                });
    }

    @Override
    protected Node convertToNode(Region object, Element element) {
        if ((object == null) || (element == null)) {
            return element;
        }

        element.setAttribute("id", "id" + object.getId());
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
        region.setId(Long.parseLong(element.getAttribute("id").substring(2)));
        region.setRegionName(element.getAttribute("name"));

        region.setCountry(new Country());
        region.getCountry().setId(Long.parseLong(element.getAttribute("countryID")));

        return region;
    }

}
