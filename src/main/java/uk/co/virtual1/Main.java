package uk.co.virtual1;

import uk.co.virtual1.model.xml.out.BritishAddress;
import uk.co.virtual1.model.xml.out.BuyerParty;
import uk.co.virtual1.model.xml.out.Constants;
import uk.co.virtual1.model.xml.out.DetailedContact;
import uk.co.virtual1.model.xml.out.EADFeatures;
import uk.co.virtual1.model.xml.out.EADProvision;
import uk.co.virtual1.model.xml.out.EADProvisionGeneral;
import uk.co.virtual1.model.xml.out.EADProvisionServiceRequestOrder;
import uk.co.virtual1.model.xml.out.Identifier;
import uk.co.virtual1.model.xml.out.Location;
import uk.co.virtual1.model.xml.out.OrderDetail;
import uk.co.virtual1.model.xml.out.OrderHeader;
import uk.co.virtual1.model.xml.out.OrderParty;
import uk.co.virtual1.model.xml.out.OrderReference;
import uk.co.virtual1.model.xml.out.PurchaseOrder;
import uk.co.virtual1.model.xml.out.Site;
import uk.co.virtual1.model.xml.out.SupplierPartyDetails;
import uk.co.virtual1.service.SoapSerializer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

/**
 * @author Mikhail Tkachenko created on 22.08.16 10:01
 */
public class Main {

    public static void main(String[] args) {
        SoapSerializer serializer = new SoapSerializer();


        PurchaseOrder purchaseOrder = new PurchaseOrder();

        OrderHeader orderHeader = orderHeader();
        purchaseOrder.setOrderHeader(orderHeader);

        purchaseOrder.getListOfOrderDetail().add(orderDetail());


        String s = serializer.serialize(purchaseOrder);
        System.out.println(serializer.pretty(s));

    }

    private static OrderHeader orderHeader() {
        OrderHeader orderHeader = new OrderHeader();

        {
            OrderReference orderReference = orderHeader.getOrderReference();
            orderReference.getAccountCode().getReference().setRefNum("AccountCode");
            orderReference.getBuyerRefNum().getReference().setRefNum("BuyerRefNum");
        }

        {
            OrderParty orderParty = orderHeader.getOrderParty();

            {
                BuyerParty buyerParty = orderParty.getBuyerParty();
                buyerParty.getParty().setPartyID("219966525");
                buyerParty.getParty().getOrderContact().setDetailedContact(detailedContact());
            }

            {
                SupplierPartyDetails supplierPartyDetails = orderParty.getSupplierParty().getParty();
                supplierPartyDetails.getListOfIdentifier().add(new Identifier());
            }
        }

        orderHeader.setPoIssuedDate(calendar());
        orderHeader.setRequestedDeliveryDate(calendar());
        return orderHeader;
    }

    private static DetailedContact detailedContact() {
        DetailedContact detailedContact = new DetailedContact();
        detailedContact.setContactName("setContactName");
        detailedContact.setTelephone("setTelephone");
        detailedContact.setEmail("setEmail");
        detailedContact.setTitle("setTitle");
        detailedContact.setFirstName("setFirstName");
        detailedContact.setInitials("setInitials");
        return detailedContact;
    }

    private static OrderDetail orderDetail() {
        OrderDetail orderDetail = new OrderDetail();

        {
            EADProvisionServiceRequestOrder requestOrder = new EADProvisionServiceRequestOrder();
            orderDetail.setServiceRequestOrder(requestOrder);

            requestOrder.getSupplierPartNum().getPartNum().setPartID(Constants.ETHERNET_ACCESS_DIRECT_OR);
            requestOrder.setSiteA(site());
            requestOrder.setSiteB(site());

            requestOrder.getLineItemReference().setBuyerLineReference("customer line item reference");

            EADFeatures eadFeatures = new EADFeatures();
            requestOrder.setFeatures(eadFeatures);

            EADProvisionGeneral provisionGeneral = eadFeatures.getProvision().getProvisionGeneral();
            provisionGeneral.setEadServiceRequired("setEadServiceRequired");
            provisionGeneral.setCusllf("setCusllf");
            provisionGeneral.setUserPortFaultPropogation("UserPortFaultPropogation");
            provisionGeneral.setContractTerm("ContactTerm");
            provisionGeneral.setSeparateFromCircuitNumber("setSeparateFromCircuitNumber");
        }

        return orderDetail;
    }

    private static EADProvision eadProvision() {
        EADProvision eadProvision = new EADProvision();
        eadProvision.setA13AmpDbleScktWithin1Mtr("N");
        eadProvision.setHousing("CABINET");

        return eadProvision;
    }

    private static Site site() {
        Site site = new Site();

        site.setCompanyName("setCompanyName");

        BritishAddress britishAddress = site.getAddress().getBritishAddress();
        britishAddress.setPremisesName("setPremisesName");
        britishAddress.setThoroughfareNumber("1");
        britishAddress.setThoroughfareName("setThoroughfareName");
        britishAddress.setPostTown("setPostTown");
        britishAddress.setPostCode("setPostCode");

        site.setOnSiteTelephone("+380639675605");

        Location location = site.getLocation();
        location.setFloor(1);
        location.setRoom("1");
        location.setPosition("jopa");

        site.setDetailedContact(detailedContact());

        return site;
    }


    private static XMLGregorianCalendar calendar() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException();
        }
    }
}
