package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mikhail Tkachenko created on 22.08.16 11:33
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class OrderDetail {
    @XmlElement(name = "ServiceRequestOrder", namespace = Constants.SERVICE_REQUEST_ORDER_NS)
    private ServiceRequestOrder serviceRequestOrder;

    public ServiceRequestOrder getServiceRequestOrder() {
        return serviceRequestOrder;
    }

    public void setServiceRequestOrder(ServiceRequestOrder serviceRequestOrder) {
        this.serviceRequestOrder = serviceRequestOrder;
    }
}
