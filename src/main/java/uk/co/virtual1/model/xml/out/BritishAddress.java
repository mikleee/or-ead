package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:34
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class BritishAddress {
    @XmlElement(name = "PremisesName", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String premisesName;
    @XmlElement(name = "ThoroughfareNumber", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String thoroughfareNumber;
    @XmlElement(name = "ThoroughfareName", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String thoroughfareName;
    @XmlElement(name = "PostTown", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String postTown;
    @XmlElement(name = "PostCode", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String postCode;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }

    public String getPremisesName() {
        return premisesName;
    }

    public void setPremisesName(String premisesName) {
        this.premisesName = premisesName;
    }

    public String getThoroughfareName() {
        return thoroughfareName;
    }

    public void setThoroughfareName(String thoroughfareName) {
        this.thoroughfareName = thoroughfareName;
    }

    public String getThoroughfareNumber() {
        return thoroughfareNumber;
    }

    public void setThoroughfareNumber(String thoroughfareNumber) {
        this.thoroughfareNumber = thoroughfareNumber;
    }
}
