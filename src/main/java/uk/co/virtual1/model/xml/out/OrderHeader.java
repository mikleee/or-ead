package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author Mikhail Tkachenko created on 22.08.16 9:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class OrderHeader {
    @XmlElement(name = "POIssuedDate")
    private XMLGregorianCalendar poIssuedDate;
    @XmlElement(name = "RequestedDeliveryDate")
    private XMLGregorianCalendar requestedDeliveryDate;
    @XmlElement(name = "OrderReference")
    private OrderReference orderReference = new OrderReference();
    @XmlElement(name = "OrderParty")
    private OrderParty orderParty = new OrderParty();

    @XmlElement(name = "OrderReference")
    private String orderCurrency = "GBR";
    @XmlElement(name = "OrderLanguage")
    private String orderLanguage = "en";
    @XmlElement(name = "PartialShipmentAllowed")
    private Boolean partialShipmentAllowed = Boolean.FALSE;


    public OrderReference getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(OrderReference orderReference) {
        this.orderReference = orderReference;
    }

    public XMLGregorianCalendar getPoIssuedDate() {
        return poIssuedDate;
    }

    public void setPoIssuedDate(XMLGregorianCalendar poIssuedDate) {
        this.poIssuedDate = poIssuedDate;
    }

    public XMLGregorianCalendar getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    public void setRequestedDeliveryDate(XMLGregorianCalendar requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public String getOrderLanguage() {
        return orderLanguage;
    }

    public void setOrderLanguage(String orderLanguage) {
        this.orderLanguage = orderLanguage;
    }

    public Boolean getPartialShipmentAllowed() {
        return partialShipmentAllowed;
    }

    public void setPartialShipmentAllowed(Boolean partialShipmentAllowed) {
        this.partialShipmentAllowed = partialShipmentAllowed;
    }

    public OrderParty getOrderParty() {
        return orderParty;
    }

    public void setOrderParty(OrderParty orderParty) {
        this.orderParty = orderParty;
    }
}
