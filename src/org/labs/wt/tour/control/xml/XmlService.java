
package org.labs.wt.tour.control.xml;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.labs.wt.tour.control.FilterListener;
import org.labs.wt.tour.model.Identifier;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


abstract class XmlService<T extends Identifier> {

    private static final Logger LOGGER = LogManager.getLogger(XmlService.class);


    private final String rootTag;
    private final String file;

    private DocumentBuilder documentBuilder = null;
    private TransformerFactory transformerFactory = null;

    private XPathFactory xPathFactory = null;
    private XPath xPath = null;

    private List<T> objects = null;


    protected abstract Node convertToNode(T object, Element element);

    protected abstract T convertToObject(Node node);

    protected abstract String getElementName();


    protected XmlService(final String rootTag, final String file) {
        this.rootTag = rootTag;
        this.file = file;

        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            transformerFactory = TransformerFactory.newInstance();

            xPathFactory = XPathFactory.newInstance();
            xPath = xPathFactory.newXPath();

            Path path = Paths.get(file);
            if (!Files.exists(path)) {
                if ((path.getParent() != null) && (!Files.exists(path.getParent()))) {
                    Files.createDirectories(path.getParent());
                }

                Document doc = documentBuilder.newDocument();
                Element rootElement = doc.createElementNS(null, rootTag);
                doc.appendChild(rootElement);

                saveDocument(doc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected List<T> getAllObjects() {
        if (objects != null) {
            return objects;
        }

        objects = new ArrayList<>();

        Document document = getDocument();
        if (document == null) {
            return objects;
        }

        NodeList nodes = getDocument().getElementsByTagName(getElementName());
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            T obj = convertToObject(node);

            if (obj != null) {
                objects.add(obj);
            }
        }

        return objects;
    }

    protected List<T> filterObjects(FilterListener filter) {
        List<T> filtered = new ArrayList<>();

        for (T obj : getAllObjects()) {
            if (filter.filter(obj)) {
                filtered.add(obj);
            }
        }

        return filtered;
    }

    protected T getObjectByID(long id) {
        List<T> list = getAllObjects();

        for (T obj : list) {
            if (obj.getId() == id) {
                return obj;
            }
        }

        //Document document = getDocument();
        //if (document == null) {
        //    return null;
        //}

        //Element element = getElementByID(document, id);
        //if (element != null) {
        //    return convertToObject(element);
        //}

        return null;
    }

    protected boolean addObject(T object) {
        List<T> list = getAllObjects();

        for (T obj : list) {
            if (obj.getId() == object.getId()) {
                return false;
            }
        }

        Document document = getDocument();
        if (document == null) {
            return false;
        }

        Node node = document.getDocumentElement();
        if (node != null) {
            node.appendChild(convertToNode(object, document.createElement(getElementName())));

            saveDocument(document);
            objects.add(object);
        }

        return true;
    }

    protected boolean updateObject(T object) {
        List<T> list = getAllObjects();

        T objToUpdate = null;

        for (T obj : list) {
            if (obj.getId() == object.getId()) {
                objToUpdate = obj;
            }
        }

        if (objToUpdate == null) {
            return false;
        }

        Document document = getDocument();
        if (document == null) {
            return false;
        }

        Element element = getElementByID(document, object.getId());
        if (element != null) {
            document.getDocumentElement().replaceChild(convertToNode(object, document.createElement(getElementName())), element);

            saveDocument(document);
            objects = null;

            return true;
        }

        return false;
    }

    protected boolean deleteObjectByID(long id) {
        List<T> list = getAllObjects();

        T objToDelete = null;

        for (T obj : list) {
            if (obj.getId() == id) {
                objToDelete = obj;
            }
        }

        if (objToDelete == null) {
            return false;
        }

        Document document = getDocument();
        if (document == null) {
            return false;
        }

        Element element = getElementByID(document, id);
        if (element != null) {
            document.getDocumentElement().removeChild(element);

            saveDocument(document);
            objects = null;
            return true;
        }

        return false;
    }

    private synchronized Document getDocument() {
        try {
            if (validateWithXsd()) {
                return documentBuilder.parse(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private boolean validateWithXsd() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL url = getClass().getResource("/xsd/" + rootTag + ".xsd");
            Schema schema = factory.newSchema(new File(url.getFile()));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
        } catch (Exception ex) {
            LOGGER.error("xsd validation error", ex);
            return false;
        }

        LOGGER.debug("xml file is valid: {}", file);

        return true;
    }

    private synchronized boolean saveDocument(Document document) {
        try {
            Transformer transformer = transformerFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //transformer.setOutputProperty(OutputKeys.METHOD, "xml");

            //document.normalize();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    private Element getElementByID(final Document document, final long id) {
        try {
            XPathExpression expr = xPath.compile("//*[@id = 'id" + id + "']");
            return (Element) expr.evaluate(document, XPathConstants.NODE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
