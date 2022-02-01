
import java.io.Serializable;
import java.util.Objects;

public class Contact implements Comparable<Contact>{

    private  String firstname;
    private String lastname;
    private Type type;
    private String number;

    public  String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname (String lastname) {
        this.lastname = lastname;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact() {
    }

    public Contact(String firstname, String lastname, Type type, String number) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return number.equals(contact.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", type=" + type +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public int compareTo(Contact o) {

        return Character.compare(this.firstname.charAt(0),o.firstname.charAt(0));
    }
}
