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
public class EADLineItemReference {
    @XmlElement(name = "BuyerLineReference", namespace = Constants.EAD_PRODUCT_NS)
    private String buyerLineReference;

    public String getBuyerLineReference() {
        return buyerLineReference;
    }

    public void setBuyerLineReference(String buyerLineReference) {
        this.buyerLineReference = buyerLineReference;
    }
}
