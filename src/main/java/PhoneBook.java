import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PhoneBook {

    private Set<Contact> contacts;

    public PhoneBook() {
        contacts = new HashSet<>();
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public boolean deleteContact(Contact contact) throws ParserConfigurationException, TransformerException {
        boolean isDeleted = false;
        Contact con = contact;
        if (contacts.remove(con)) {

            XMLParser.writeFile(Constant.FILE_PATH, contacts);
            isDeleted = true;
        }
        return isDeleted;
    }

    public boolean insertContact(Contact contact) throws ParserConfigurationException, TransformerException {
        boolean isAdded = false;

        if (contacts.add(contact)) {
            System.out.println("insert " + contacts);
            XMLParser.writeFile(Constant.FILE_PATH, contacts);
            isAdded = true;
        }
        return isAdded;
    }

    public void readContacts() throws ParserConfigurationException, IOException, SAXException {
        contacts.clear();
        contacts = XMLParser.readFile(Constant.FILE_PATH);
    }

    public void editContact(int index, Contact contact) throws ParserConfigurationException, TransformerException {
        List<Contact> c = contacts.stream().collect(Collectors.toList());
        try {
            c.set(index, contact);
            XMLParser.writeFile(Constant.FILE_PATH, c);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Contact with index " + index + " doesn't exist!");
        }

    }

    public List<Contact> sortedContactList() {
        List<Contact> contactList = contacts.stream().collect(Collectors.toList());

        return contactList.stream().sorted(Comparator.comparing(Contact::getFirstname)).collect(Collectors.toList());
    }

    public List<Contact> sortedReverseContactList() {
        List<Contact> contactList = contacts.stream().collect(Collectors.toList());

        return contactList.stream().sorted((c1, c2) -> c2.getFirstname().compareTo(c1.getFirstname())).collect(Collectors.toList());
    }

}
