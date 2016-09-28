package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 9:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class DetailedContact {
    @XmlElement(name = "ContactName")
    private String contactName;
    @XmlElement(name = "Telephone")
    private String telephone;
    @XmlElement(name = "Email")
    private String email;
    @XmlElement(name = "Title", namespace = Ns.SERVICE_REQUEST_ORDER_NS)
    private String title;
    @XmlElement(name = "FirstName", namespace = Ns.SERVICE_REQUEST_ORDER_NS)
    private String firstName;
    @XmlElement(name = "Initials", namespace = Ns.SERVICE_REQUEST_ORDER_NS)
    private String initials;


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
