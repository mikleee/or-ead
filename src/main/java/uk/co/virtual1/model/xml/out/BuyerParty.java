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
public class BuyerParty {
    @XmlElement(name = "Party")
    private BuyerPartyDetails party = new BuyerPartyDetails();

    public BuyerPartyDetails getParty() {
        return party;
    }

    public void setParty(BuyerPartyDetails party) {
        this.party = party;
    }
}
