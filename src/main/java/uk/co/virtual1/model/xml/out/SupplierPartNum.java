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
public class SupplierPartNum {
    @XmlElement(name = "PartNum")
    private PartNum partNum = new PartNum();

    public PartNum getPartNum() {
        return partNum;
    }

    public void setPartNum(PartNum partNum) {
        this.partNum = partNum;
    }
}
