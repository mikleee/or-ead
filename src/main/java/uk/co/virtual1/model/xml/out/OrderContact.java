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
public class OrderContact {
    @XmlElement(name = "DetailedContact", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private DetailedContact detailedContact = new DetailedContact();

    public DetailedContact getDetailedContact() {
        return detailedContact;
    }

    public void setDetailedContact(DetailedContact detailedContact) {
        this.detailedContact = detailedContact;
    }
}
