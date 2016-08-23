package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 10:31
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class SupplierParty {
    @XmlElement(name = "Party")
    private SupplierPartyDetails party = new SupplierPartyDetails();

    public SupplierPartyDetails getParty() {
        return party;
    }

    public void setParty(SupplierPartyDetails party) {
        this.party = party;
    }
}
