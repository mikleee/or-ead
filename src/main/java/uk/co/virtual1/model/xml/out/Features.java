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
@XmlSeeAlso({EADFeatures.class})
public abstract class Features<T extends ProvisionWrapper> {

    public abstract T getProvision();

    public abstract void setProvision(T provision);
}
