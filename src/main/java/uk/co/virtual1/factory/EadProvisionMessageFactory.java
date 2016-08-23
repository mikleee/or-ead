package uk.co.virtual1.factory;

import org.springframework.stereotype.Service;
import uk.co.virtual1.model.xml.out.BuyerParty;
import uk.co.virtual1.model.xml.out.Constants;
import uk.co.virtual1.model.xml.out.EADFeatures;
import uk.co.virtual1.model.xml.out.EADProvision;
import uk.co.virtual1.model.xml.out.EADProvisionServiceRequestOrder;
import uk.co.virtual1.model.xml.out.OrderDetail;
import uk.co.virtual1.model.xml.out.OrderHeader;
import uk.co.virtual1.model.xml.out.OrderParty;
import uk.co.virtual1.model.xml.out.OrderReference;
import uk.co.virtual1.model.xml.out.PurchaseOrder;
import uk.co.virtual1.model.xml.out.Site;
import uk.co.virtual1.salesforce.object.Access;
import uk.co.virtual1.salesforce.object.Case;

/**
 * @author Mikhail Tkachenko created on 23.08.16 14:04
 */
@Service
public class EadProvisionMessageFactory extends MessageFactory {

    public PurchaseOrder createObject(Case sfCase) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        OrderHeader orderHeader = createOrderHeader(sfCase);
        purchaseOrder.setOrderHeader(orderHeader);

        OrderDetail orderDetail = createOrderDetail(sfCase);
        purchaseOrder.getListOfOrderDetail().add(orderDetail);

        return purchaseOrder;
    }


    private OrderHeader createOrderHeader(Case sfCase) {
        OrderHeader orderHeader = new OrderHeader();

        orderHeader.setPoIssuedDate(factoryUtils.calendar());
        orderHeader.setRequestedDeliveryDate(factoryUtils.calendar(sfCase.getOrderReceived(), 100)); // TODO: 23.08.16  Order received date + number of working days (Salesforce config option)

        // orderReference
        OrderReference orderReference = orderHeader.getOrderReference();
        orderReference.getAccountCode().getReference().setRefNum("AccountCode"); // TODO: 23.08.16
        orderReference.getBuyerRefNum().getReference().setRefNum(factoryUtils.buyerRefNum(sfCase));


        // orderReference
        OrderParty orderParty = orderHeader.getOrderParty();

        BuyerParty buyerParty = orderParty.getBuyerParty();
        buyerParty.getParty().setPartyID("219966525"); // TODO: 23.08.16
        buyerParty.getParty().getOrderContact().setDetailedContact(virtual1DetailedContact());

        return orderHeader;
    }

    private OrderDetail createOrderDetail(Case aCase) {
        OrderDetail orderDetail = new OrderDetail();

        EADProvisionServiceRequestOrder requestOrder = new EADProvisionServiceRequestOrder();
        orderDetail.setServiceRequestOrder(requestOrder);

        requestOrder.getSupplierPartNum().getPartNum().setPartID(Constants.ETHERNET_ACCESS_DIRECT_OR);

        if (aCase.getAccess().getSiteAEnd() != null) {
            Site site = createSite(aCase.getAccess().getSiteAEnd());
            site.setDetailedContact(virtual1DetailedContact());
            requestOrder.setSiteA(site);
        }
        if (aCase.getAccess().getSiteBEnd() != null) {
            Site site = createSite(aCase.getAccess().getSiteBEnd());
            requestOrder.setSiteB(site);
        }

        requestOrder.getLineItemReference().setBuyerLineReference("customer line item reference");

        EADFeatures eadFeatures = createFeatures(aCase);
        requestOrder.setFeatures(eadFeatures);

        return orderDetail;
    }

    private EADFeatures createFeatures(Case aCase) {
        Access access = aCase.getAccess();

        EADFeatures eadFeatures = new EADFeatures();

        EADProvision provisionAEnd = eadFeatures.getProvision().getProvisionAEnd();
        provisionAEnd.setA13AmpDbleScktWithin1Mtr(Constants.Y);
        provisionAEnd.setHousing(Constants.HOUSING_CABINET);
        provisionAEnd.setSiteType(Constants.SITE_TYPE_BT);
        provisionAEnd.setTelephone3m(Constants.N);
        provisionAEnd.setThirdPartyAccess(Constants.N);
        provisionAEnd.setCctNoOfFibreService("todo");
        provisionAEnd.setCircuitInterfaceType(factoryUtils.circuitInterfaceType(access));
        provisionAEnd.setExistingFibreServiceatSite(Constants.Y);
        provisionAEnd.setFibreServiceSameLocation(Constants.Y);
        provisionAEnd.setFloorLoc("todo");
        provisionAEnd.setRackLoc("todo");
        provisionAEnd.setRoomLoc("todo");
        provisionAEnd.setSlotLoc("todo");
        provisionAEnd.setSuiteLoc("todo");
        provisionAEnd.setCommsRoomReady(Constants.Y);
        provisionAEnd.setLandlordConsentGranted(Constants.Y);
        provisionAEnd.setLandLordConsent(Constants.N);
        provisionAEnd.setNteChassisOptionReqdAEnd("4U 15 Slot Modular");
        provisionAEnd.setPowerSupplyRequired("240 VOLTS");

        return eadFeatures;
    }


}
