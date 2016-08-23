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
@XmlType
public class Address {
    @XmlAttribute(name = "type")
    private String type = Constants.ADDRESS_TYPE_FULL;
    @XmlElement(name = "BritishAddress", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private BritishAddress britishAddress = new BritishAddress();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BritishAddress getBritishAddress() {
        return britishAddress;
    }

    public void setBritishAddress(BritishAddress britishAddress) {
        this.britishAddress = britishAddress;
    }
}
