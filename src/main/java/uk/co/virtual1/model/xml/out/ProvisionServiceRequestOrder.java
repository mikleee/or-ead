package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 12:36
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlSeeAlso({EADProvisionServiceRequestOrder.class})
public abstract class ProvisionServiceRequestOrder extends ServiceRequestOrder {
    @XmlElement(name = "LineItemNum")
    private String lineItemNum;
    @XmlElement(name = "SupplierPartNum")
    private SupplierPartNum supplierPartNum = new SupplierPartNum();
    @XmlElement(name = "Quantity")
    private Quantity quantity = new Quantity();
    @XmlElement(name = "Site", namespace = Ns.SERVICE_REQUEST_ORDER_NS)
    private Site siteA;
    @XmlElement(name = "Site", namespace = Ns.SERVICE_REQUEST_ORDER_NS)
    private Site siteB;
    @XmlElement(name = "Features", namespace = Ns.SERVICE_REQUEST_ORDER_NS)
    private Features features;

    public ProvisionServiceRequestOrder() {
        itemProperty = "Provision";
    }

    public String getLineItemNum() {
        return lineItemNum;
    }

    public void setLineItemNum(String lineItemNum) {
        this.lineItemNum = lineItemNum;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public SupplierPartNum getSupplierPartNum() {
        return supplierPartNum;
    }

    public void setSupplierPartNum(SupplierPartNum supplierPartNum) {
        this.supplierPartNum = supplierPartNum;
    }

    public Site getSiteA() {
        return siteA;
    }

    public void setSiteA(Site siteA) {
        if (siteA != null) {
            siteA.setEnd(Constants.END_A);
        }
        this.siteA = siteA;
    }

    public Site getSiteB() {
        return siteB;
    }

    public void setSiteB(Site siteB) {
        if (siteB != null) {
            siteB.setEnd(Constants.END_B);
        }
        this.siteB = siteB;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }
}
