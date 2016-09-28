package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author Mikhail Tkachenko created on 22.08.16 11:33
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class OrderDetail {
    @XmlElement(name = "ServiceRequestOrder", namespace = Ns.SERVICE_REQUEST_ORDER_NS)
    private ServiceRequestOrder serviceRequestOrder;
    @XmlElement(name = "SpecialHandlingNote")
    private String specialHandlingNote;
    @XmlElement(name = "GeneralNote")
    private String generalNote;
    @XmlElement(name = "RequestedDeliveryDate")
    private XMLGregorianCalendar requestedDeliveryDate;
    @XmlElement(name = "BuyerExpectedUnitPrice")
    private BuyerExpectedUnitPrice buyerExpectedUnitPrice = new BuyerExpectedUnitPrice();

    public ServiceRequestOrder getServiceRequestOrder() {
        return serviceRequestOrder;
    }

    public void setServiceRequestOrder(ServiceRequestOrder serviceRequestOrder) {
        this.serviceRequestOrder = serviceRequestOrder;
    }

    public String getSpecialHandlingNote() {
        return specialHandlingNote;
    }

    public void setSpecialHandlingNote(String specialHandlingNote) {
        this.specialHandlingNote = specialHandlingNote;
    }

    public String getGeneralNote() {
        return generalNote;
    }

    public void setGeneralNote(String generalNote) {
        this.generalNote = generalNote;
    }

    public XMLGregorianCalendar getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    public void setRequestedDeliveryDate(XMLGregorianCalendar requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
    }

    public BuyerExpectedUnitPrice getBuyerExpectedUnitPrice() {
        return buyerExpectedUnitPrice;
    }

    public void setBuyerExpectedUnitPrice(BuyerExpectedUnitPrice buyerExpectedUnitPrice) {
        this.buyerExpectedUnitPrice = buyerExpectedUnitPrice;
    }
}
