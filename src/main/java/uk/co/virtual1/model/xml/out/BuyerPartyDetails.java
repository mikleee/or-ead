package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 10:31
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class BuyerPartyDetails {
    @XmlAttribute(name = "PartyID")
    private String partyID;
    @XmlAttribute(name = "AgencyID")
    private String agencyID = "Other";
    @XmlAttribute(name = "AgencyOther")
    private String agencyOther = "Assigned by Supplier";
    @XmlElement(name = "OrderContact")
    private OrderContact orderContact = new OrderContact();

    public String getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(String agencyID) {
        this.agencyID = agencyID;
    }

    public String getAgencyOther() {
        return agencyOther;
    }

    public void setAgencyOther(String agencyOther) {
        this.agencyOther = agencyOther;
    }

    public String getPartyID() {
        return partyID;
    }

    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    public OrderContact getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(OrderContact orderContact) {
        this.orderContact = orderContact;
    }

}
