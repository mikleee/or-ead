package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:01
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlSeeAlso({EADProvisionWrapper.class})
public abstract class ProvisionWrapper {

    public abstract EADProvision getProvisionAEnd();

    public abstract void setProvisionAEnd(EADProvision provisionAEnd);

    public abstract EADProvision getProvisionBEnd();

    public abstract void setProvisionBEnd(EADProvision provisionBEnd);

    public abstract EADProvisionGeneral getProvisionGeneral();

    public abstract void setProvisionGeneral(EADProvisionGeneral provisionGeneral);
}
