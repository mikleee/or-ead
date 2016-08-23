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
public class EADProvisionWrapper extends ProvisionWrapper {
    @XmlElement(name = "ProvisionAEnd", namespace = Constants.EAD_PRODUCT_NS)
    private EADProvision provisionAEnd = new EADProvision();
    @XmlElement(name = "ProvisionBEnd", namespace = Constants.EAD_PRODUCT_NS)
    private EADProvision provisionBEnd = new EADProvision();
    @XmlElement(name = "ProvisionGeneral", namespace = Constants.EAD_PRODUCT_NS)
    private EADProvisionGeneral provisionGeneral = new EADProvisionGeneral();

    public EADProvision getProvisionAEnd() {
        return provisionAEnd;
    }

    public void setProvisionAEnd(EADProvision provisionAEnd) {
        this.provisionAEnd = provisionAEnd;
    }

    public EADProvision getProvisionBEnd() {
        return provisionBEnd;
    }

    public void setProvisionBEnd(EADProvision provisionBEnd) {
        this.provisionBEnd = provisionBEnd;
    }

    public EADProvisionGeneral getProvisionGeneral() {
        return provisionGeneral;
    }

    public void setProvisionGeneral(EADProvisionGeneral provisionGeneral) {
        this.provisionGeneral = provisionGeneral;
    }
}
