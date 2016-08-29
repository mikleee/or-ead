package uk.co.virtual1.factory;

import org.springframework.stereotype.Service;
import uk.co.virtual1.exception.ProvisioningException;
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
import uk.co.virtual1.salesforcebox.object.Access;
import uk.co.virtual1.salesforcebox.object.Case;

import java.math.BigDecimal;

import static uk.co.virtual1.service.EnvironmentKey.*;

/**
 * @author Mikhail Tkachenko created on 23.08.16 14:04
 */
@Service
public class EadProvisionMessageFactory extends MessageFactory {

    public PurchaseOrder createObject(Case sfCase) throws ProvisioningException {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        OrderHeader orderHeader = createOrderHeader(sfCase);
        purchaseOrder.setOrderHeader(orderHeader);

        OrderDetail orderDetail = createOrderDetail(sfCase);
        purchaseOrder.getListOfOrderDetail().add(orderDetail);

        BigDecimal totalAmount = factoryUtils.totalAmount(purchaseOrder.getListOfOrderDetail());
        purchaseOrder.getOrderSummary().setTotalAmount(totalAmount);
        return purchaseOrder;
    }


    private OrderHeader createOrderHeader(Case sfCase) {
        OrderHeader orderHeader = new OrderHeader();

        orderHeader.setPoIssuedDate(factoryUtils.calendar());
        orderHeader.setRequestedDeliveryDate(factoryUtils.requestedDeliveryDate(sfCase));

        // orderReference
        OrderReference orderReference = orderHeader.getOrderReference();
        orderReference.getAccountCode().getReference().setRefNum(environment.get(VIRTUAL1_ACCOUNT_REF_NUM));
        orderReference.getBuyerRefNum().getReference().setRefNum(factoryUtils.buyerRefNum(sfCase));


        // orderReference
        OrderParty orderParty = orderHeader.getOrderParty();

        BuyerParty buyerParty = orderParty.getBuyerParty();
        buyerParty.getParty().setPartyID(environment.get(VIRTUAL1_BUYER_PARTY_ID));
        buyerParty.getParty().getOrderContact().setDetailedContact(virtual1DetailedContact);

        return orderHeader;
    }

    private OrderDetail createOrderDetail(Case sfCase) throws ProvisioningException {
        Access access = sfCase.getAccess();
        OrderDetail orderDetail = new OrderDetail();

        EADProvisionServiceRequestOrder requestOrder = new EADProvisionServiceRequestOrder();
        orderDetail.setServiceRequestOrder(requestOrder);
        requestOrder.setLineItemNum("1");
        requestOrder.getQuantity().setQty("1");
        requestOrder.getQuantity().getUnitOfMeasure().setUof("EA");

        requestOrder.getSupplierPartNum().getPartNum().setPartID(Constants.ETHERNET_ACCESS_DIRECT_OR);


        Site siteAEnd = createSiteAEnd(sfCase);
        requestOrder.setSiteA(siteAEnd);

        Site siteBEnd = createSiteBEnd(sfCase);
        requestOrder.setSiteB(siteBEnd);

        requestOrder.getLineItemReference().setBuyerLineReference("1");

        EADFeatures eadFeatures = createFeatures(access);
        requestOrder.setFeatures(eadFeatures);

        orderDetail.setSpecialHandlingNote(Constants.BLANK);
        orderDetail.setGeneralNote(factoryUtils.generalNote(sfCase)); // TODO: 24.08.2016  
        orderDetail.setRequestedDeliveryDate(factoryUtils.requestedDeliveryDate(sfCase));
        orderDetail.getBuyerExpectedUnitPrice().getPrice().setUnitPrice(factoryUtils.unitPrice(access));

        return orderDetail;
    }

    private EADFeatures createFeatures(Access access) {
        EADFeatures eadFeatures = new EADFeatures();

        EADProvision provisionAEnd = createProvisionAEnd(access);
        eadFeatures.getProvision().setProvisionAEnd(provisionAEnd);

        EADProvision provisionBEnd = createProvisionBEnd(access);
        eadFeatures.getProvision().setProvisionBEnd(provisionBEnd);

        return eadFeatures;
    }

    private EADProvision createProvisionAEnd(Access access) {
        EADProvision provisionAEnd = new EADProvision();
        provisionAEnd.setA13AmpDbleScktWithin1Mtr(Constants.Y);
        provisionAEnd.setHousing(Constants.HOUSING_CABINET);
        provisionAEnd.setSiteType(Constants.SITE_TYPE_BT);
        provisionAEnd.setTelephone3m(Constants.N);
        provisionAEnd.setThirdPartyAccess(Constants.N);
        provisionAEnd.setCctNoOfFibreService(Constants.BLANK);
        provisionAEnd.setCircuitInterfaceType(factoryUtils.circuitInterfaceType(access));
        provisionAEnd.setExistingFibreServiceatSite(Constants.Y);
        provisionAEnd.setFibreServiceSameLocation(Constants.Y);
        provisionAEnd.setFloorLoc("1"); //todo value need to be clarified
        provisionAEnd.setRackLoc("303"); //todo value need to be clarified
        provisionAEnd.setRoomLoc("15/16"); //todo value need to be clarified
        provisionAEnd.setSlotLoc(Constants.BLANK); //todo value need to be clarified
        provisionAEnd.setSuiteLoc(Constants.BLANK); //todo value need to be clarified
        provisionAEnd.setCommsRoomReady(Constants.Y);
        provisionAEnd.setLandlordConsentGranted(Constants.Y);
        provisionAEnd.setLandLordConsent(Constants.N);
        provisionAEnd.setNteChassisOptionReqdAEnd("4U 15 Slot Modular");
        provisionAEnd.setPowerSupplyRequired("240 VOLTS");
        return provisionAEnd;
    }

    private EADProvision createProvisionBEnd(Access access) {
        EADProvision provisionBEnd = new EADProvision();
        provisionBEnd.setA13AmpDbleScktWithin1Mtr(Constants.Y);
        provisionBEnd.setHousing(Constants.HOUSING_CABINET);
        provisionBEnd.setSiteType(Constants.SITE_TYPE_NON_BT);
        provisionBEnd.setTelephone3m(Constants.Y);
        provisionBEnd.setThirdPartyAccess("Obtained");
        provisionBEnd.setCctNoOfFibreService(Constants.N);
        provisionBEnd.setCircuitInterfaceType(factoryUtils.circuitInterfaceType(access));
        provisionBEnd.setExistingFibreServiceatSite(Constants.N);
        provisionBEnd.setFibreServiceSameLocation(Constants.N);
        provisionBEnd.setFloorLoc(access.getFloorBEnd());
        provisionBEnd.setRackLoc(access.getRackBEnd());
        provisionBEnd.setRoomLoc(access.getRoomBEnd());
        provisionBEnd.setSlotLoc(Constants.BLANK);
        provisionBEnd.setSuiteLoc(Constants.BLANK);
        provisionBEnd.setCommsRoomReady(Constants.Y);
        provisionBEnd.setLandlordConsentGranted(Constants.Y);
        provisionBEnd.setLandLordConsent(Constants.N);
        provisionBEnd.setNteChassisOptionReqdAEnd("1u single port NTE");
        provisionBEnd.setPowerSupplyRequired("240 VOLTS");
        return provisionBEnd;
    }


}
