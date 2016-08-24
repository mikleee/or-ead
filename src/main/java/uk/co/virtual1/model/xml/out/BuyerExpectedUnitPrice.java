package uk.co.virtual1.model.xml.out;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 24.08.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class BuyerExpectedUnitPrice {
    @XmlElement(name = "Price")
    private Price price = new Price();

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
