package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:34
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = Constants.SERVICE_REQUEST_ORDER_NS)
public class Site {
    @XmlAttribute(name = "End")
    private String end;
    @XmlElement(name = "CompanyName", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String companyName;
    @XmlElement(name = "Address", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private Address address = new Address();
    @XmlElement(name = "Coordinates", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private Coordinates coordinates;
    @XmlElement(name = "OnSiteTelephone", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String onSiteTelephone;
    @XmlElement(name = "Location", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private Location location = new Location();
    @XmlElement(name = "DetailedContact", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private DetailedContact detailedContact = new DetailedContact();


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public DetailedContact getDetailedContact() {
        return detailedContact;
    }

    public void setDetailedContact(DetailedContact detailedContact) {
        this.detailedContact = detailedContact;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getOnSiteTelephone() {
        return onSiteTelephone;
    }

    public void setOnSiteTelephone(String onSiteTelephone) {
        this.onSiteTelephone = onSiteTelephone;
    }
}
