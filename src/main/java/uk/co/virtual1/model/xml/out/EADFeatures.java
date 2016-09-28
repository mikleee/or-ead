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
public class EADFeatures extends Features<EADProvisionWrapper> {
    @XmlElement(name = "Provision", namespace = Ns.EAD_PRODUCT_NS)
    private EADProvisionWrapper provision = new EADProvisionWrapper();

    public EADProvisionWrapper getProvision() {
        return provision;
    }

    public void setProvision(EADProvisionWrapper provision) {
        this.provision = provision;
    }
}
