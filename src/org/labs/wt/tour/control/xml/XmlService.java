
package org.labs.wt.tour.control.xml;


import org.labs.wt.tour.model.Identifier;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


abstract class XmlService<T extends Identifier> {

    private final String rootTag;
    private final String file;

    private DocumentBuilder documentBuilder = null;
    private TransformerFactory transformerFactory = null;

    private XPathFactory xPathFactory = null;
    private XPath xPath = null;

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
        List<T> items = new ArrayList<>();

        Document document = getDocument();
        if (document == null) {
            return items;
        }

        NodeList nodes = document.getElementsByTagName(getElementName());
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            T obj = convertToObject(node);

            if (obj != null) {
                items.add(obj);
            }
        }

        return items;
    }

    protected T getObjectByID(long id) {
        Document document = getDocument();
        if (document == null) {
            return null;
        }

        Element element = getElementByID(document, id);
        if (element != null) {
            return convertToObject(element);
        }

        return null;
    }

    protected boolean addObject(T object) {
        Document document = getDocument();
        if (document == null) {
            return false;
        }

        Node node = document.getDocumentElement();
        if (node != null) {
            node.appendChild(convertToNode(object, document.createElement(getElementName())));

            return saveDocument(document);
        }

        return false;
    }

    protected boolean updateObject(T object) {
        Document document = getDocument();
        if (document == null) {
            return false;
        }

        Element element = getElementByID(document, object.getId());
        if (element != null) {
            document.getDocumentElement().replaceChild(convertToNode(object, document.createElement(getElementName())), element);

            return saveDocument(document);
        }

        return false;
    }

    protected boolean deleteObjectByID(long id) {
        Document document = getDocument();
        if (document == null) {
            return false;
        }

        Element element = getElementByID(document, id);
        if (element != null) {
            document.getDocumentElement().removeChild(element);

            return saveDocument(document);
        }

        return false;
    }

    private synchronized Document getDocument() {
        try {
            return documentBuilder.parse(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
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
            XPathExpression expr = xPath.compile("//*[@id = '" + id + "']");
            return (Element) expr.evaluate(document, XPathConstants.NODE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
