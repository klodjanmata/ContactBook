
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Contact contact = new Contact("Klodjan", "Mata", Type.CELLPHONE, "0688845568");
        Contact contact2 = new Contact("Bledi", "ALLA", Type.WORK, "19301239123");
        Contact contact3 = new Contact("Indrit", "Mata", Type.CELLPHONE, "0688845567");

        PhoneBook bookPhone = new PhoneBook();
        bookPhone.readContacts();
        bookPhone.insertContact(contact3);
        // bookPhone.insertContact(contact3);
       // bookPhone.editContact(0, contact);
        bookPhone.deleteContact(contact3);


        System.out.println(bookPhone.sortedContactList());
//        System.out.println(bookPhone.sortedReverseContactList());

    }
}
