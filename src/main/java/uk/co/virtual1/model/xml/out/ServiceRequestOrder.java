package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 12:36
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlSeeAlso({ProvisionServiceRequestOrder.class})
public abstract class ServiceRequestOrder {
    @XmlAttribute(name = "ItemType", required = true)
    String itemProperty;

    public String getItemProperty() {
        return itemProperty;
    }

    public void setItemProperty(String itemProperty) {
        this.itemProperty = itemProperty;
    }
}
