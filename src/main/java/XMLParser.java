
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

public class XMLParser{

    private static  Set<Contact> contacts = new HashSet<>();



    public static Set<Contact>  readFile(String filePath ) throws ParserConfigurationException, IOException, SAXException {

        File xmlFile = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("contact");

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            //System.out.println("\nCurrent Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) nNode;

                Node node1 = elem.getElementsByTagName("firstname").item(0);
                String fname = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("lastname").item(0);
                String lname = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("type").item(0);
                String typeN = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("number").item(0);
                String num = node4.getTextContent();

                Type type = null;

                if (typeN.equalsIgnoreCase(Type.CELLPHONE.name())){
                    type = Type.CELLPHONE;
                }else if(typeN.equalsIgnoreCase(Type.WORK.name())){
                    type = Type.WORK;
                }else {
                    type =Type.HOME;
                }

                contacts.add(new Contact(fname, lname, type, num));

            }
        }


        return contacts;
    }

    public static void writeFile(String filePath, Collection<Contact> contacts) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("contacts");
        doc.appendChild(root);

        contacts.forEach(contact -> root.appendChild(createUser(doc, contact)));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        File myFile = new File(filePath);

        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        transf.transform(source, console);
        transf.transform(source, file);
    }
    private static Node createUser(Document doc, Contact contact) {

        Element user = doc.createElement("contact");

        user.appendChild(createUserElement(doc, "firstname", contact.getFirstname()));
        user.appendChild(createUserElement(doc, "lastname", contact.getLastname()));
        user.appendChild(createUserElement(doc, "type", contact.getType().name()));
        user.appendChild(createUserElement(doc, "number", contact.getNumber()));

        return user;
    }

    private static Node createUserElement(Document doc, String name,
                                          String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }
}