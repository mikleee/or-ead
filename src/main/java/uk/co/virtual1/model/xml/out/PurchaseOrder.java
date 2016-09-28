package uk.co.virtual1.model.xml.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Tkachenko created on 22.08.16 9:45
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement(name = "PurchaseOrder", namespace = Ns.GLOBAL_NS)
public class PurchaseOrder {
    @XmlElement(name = "OrderHeader")
    private OrderHeader orderHeader = new OrderHeader();
    @XmlElement(name = "ListOfOrderDetail")
    private List<OrderDetail> listOfOrderDetail = new ArrayList<>();
    @XmlElement(name = "OrderSummary")
    private OrderSummary orderSummary = new OrderSummary();


    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public List<OrderDetail> getListOfOrderDetail() {
        return listOfOrderDetail;
    }

    public void setListOfOrderDetail(List<OrderDetail> listOfOrderDetail) {
        this.listOfOrderDetail = listOfOrderDetail;
    }

    public OrderSummary getOrderSummary() {
        return orderSummary;
    }

    public void setOrderSummary(OrderSummary orderSummary) {
        this.orderSummary = orderSummary;
    }
}
