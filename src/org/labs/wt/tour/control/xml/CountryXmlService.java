
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.control.CountryService;
import org.labs.wt.tour.control.FilterListener;
import org.labs.wt.tour.model.Country;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;


public class CountryXmlService extends XmlService<Country> implements CountryService {

    public CountryXmlService(final String file) {
        super("countries", file);
    }

    @Override
    public List<Country> getCountries() {
        return getAllObjects();
    }

    @Override
    public Country getCountryById(long id) {
        return getObjectByID(id);
    }

    @Override
    public boolean addCountry(Country country) {
        return addObject(country);
    }

    @Override
    public boolean updateCountry(Country country) {
        return updateObject(country);
    }

    @Override
    public boolean deleteCountry(long id) {
        return deleteObjectByID(id);
    }

    @Override
    public List<Country> filterCountries(Country filterCountry) {
        return filterObjects(
                (FilterListener<Country>) country -> {
                    if ((country.getCountryName() != null) &&
                            (filterCountry.getCountryName() != null) &&
                            (country.getCountryName().contains(filterCountry.getCountryName()))) {
                        return true;
                    }
                    return false;
                });
    }

    @Override
    protected String getElementName() {
        return "country";
    }

    @Override
    protected Country convertToObject(Node node) {
        if ((node == null) || (node.getNodeType() != Node.ELEMENT_NODE)) {
            return null;
        }

        Element element = (Element)node;
        Country country = new Country();
        country.setId(Long.parseLong(element.getAttribute("id")));
        country.setCountryName(element.getAttribute("name"));

        return country;
    }

    @Override
    protected Node convertToNode(Country object, Element element) {
        if ((object == null) || (element == null)) {
            return element;
        }

        element.setAttribute("id", Long.toString(object.getId()));
        element.setAttribute("name", object.getCountryName());

        return element;
    }

}
