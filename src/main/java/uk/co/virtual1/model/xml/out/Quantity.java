package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:01
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Quantity {
    @XmlElement(name = "Qty")
    private String qty;
    @XmlElement(name = "UnitOfMeasure")
    private UnitOfMeasure unitOfMeasure = new UnitOfMeasure();

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
