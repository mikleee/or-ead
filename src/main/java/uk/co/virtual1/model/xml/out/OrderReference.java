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
public class OrderReference {
    @XmlElement(name = "AccountCode")
    private ReferenceWrapper accountCode = new ReferenceWrapper();
    @XmlElement(name = "BuyerRefNum")
    private ReferenceWrapper buyerRefNum = new ReferenceWrapper();

    public ReferenceWrapper getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(ReferenceWrapper accountCode) {
        this.accountCode = accountCode;
    }

    public ReferenceWrapper getBuyerRefNum() {
        return buyerRefNum;
    }

    public void setBuyerRefNum(ReferenceWrapper buyerRefNum) {
        this.buyerRefNum = buyerRefNum;
    }
}
