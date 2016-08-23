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
public class OrderParty {
    @XmlElement(name = "BuyerParty")
    private BuyerParty buyerParty = new BuyerParty();
    @XmlElement(name = "SupplierParty")
    private SupplierParty supplierParty = new SupplierParty();

    public BuyerParty getBuyerParty() {
        return buyerParty;
    }

    public void setBuyerParty(BuyerParty buyerParty) {
        this.buyerParty = buyerParty;
    }

    public SupplierParty getSupplierParty() {
        return supplierParty;
    }

    public void setSupplierParty(SupplierParty supplierParty) {
        this.supplierParty = supplierParty;
    }
}
