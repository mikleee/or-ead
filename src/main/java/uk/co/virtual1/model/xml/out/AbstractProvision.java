package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:34
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlSeeAlso({EADProvisionGeneral.class, EADProvision.class})
public abstract class AbstractProvision {
    @XmlAttribute(name = "ProductSpecificQuestion")
    private String productSpecificQuestion;

    public String getProductSpecificQuestion() {
        return productSpecificQuestion;
    }

    public void setProductSpecificQuestion(String productSpecificQuestion) {
        this.productSpecificQuestion = productSpecificQuestion;
    }
}
