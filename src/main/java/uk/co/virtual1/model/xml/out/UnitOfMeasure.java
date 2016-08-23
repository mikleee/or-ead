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
public class UnitOfMeasure {
    @XmlElement(name = "UOF")
    private String uof = "EA";

    public String getUof() {
        return uof;
    }

    public void setUof(String uof) {
        this.uof = uof;
    }
}
