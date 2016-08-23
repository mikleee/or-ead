package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 13:34
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = Constants.SERVICE_REQUEST_ORDER_NS)
public class Coordinate {
    @XmlElement(name = "x", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String x;
    @XmlElement(name = "System", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private String system;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
