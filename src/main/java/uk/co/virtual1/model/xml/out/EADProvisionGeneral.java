package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:34
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EADProvisionGeneral extends AbstractProvision {
    @XmlAttribute(name = "EADServiceRequired")
    private String eadServiceRequired;
    @XmlAttribute(name = "CUSLLF")
    private String cusllf;
    @XmlAttribute(name = "UserPortFaultPropogation")
    private String userPortFaultPropogation;
    @XmlAttribute(name = "ContractTerm")
    private String contractTerm;
    @XmlAttribute(name = "SeparateFromCircuitNumber")
    private String separateFromCircuitNumber;

    public String getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(String contractTerm) {
        this.contractTerm = contractTerm;
    }

    public String getCusllf() {
        return cusllf;
    }

    public void setCusllf(String cusllf) {
        this.cusllf = cusllf;
    }

    public String getEadServiceRequired() {
        return eadServiceRequired;
    }

    public void setEadServiceRequired(String eadServiceRequired) {
        this.eadServiceRequired = eadServiceRequired;
    }

    public String getSeparateFromCircuitNumber() {
        return separateFromCircuitNumber;
    }

    public void setSeparateFromCircuitNumber(String separateFromCircuitNumber) {
        this.separateFromCircuitNumber = separateFromCircuitNumber;
    }

    public String getUserPortFaultPropogation() {
        return userPortFaultPropogation;
    }

    public void setUserPortFaultPropogation(String userPortFaultPropogation) {
        this.userPortFaultPropogation = userPortFaultPropogation;
    }
}
