package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 12:36
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EADProvisionServiceRequestOrder extends ProvisionServiceRequestOrder {
    @XmlElement(name = "LineItemReference", namespace = Constants.EAD_PRODUCT_NS)
    private EADLineItemReference lineItemReference = new EADLineItemReference();
    @XmlElement(name = "LineItemFlags", namespace = Constants.EAD_PRODUCT_NS)
    private LineItemFlags lineItemFlags = new LineItemFlags();

    public EADProvisionServiceRequestOrder() {
        itemProperty = "Provision";
    }

    public LineItemFlags getLineItemFlags() {
        return lineItemFlags;
    }

    public void setLineItemFlags(LineItemFlags lineItemFlags) {
        this.lineItemFlags = lineItemFlags;
    }

    public EADLineItemReference getLineItemReference() {
        return lineItemReference;
    }

    public void setLineItemReference(EADLineItemReference lineItemReference) {
        this.lineItemReference = lineItemReference;
    }
}
