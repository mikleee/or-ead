package uk.co.virtual1.salesforce;

import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.bind.XmlObject;
import com.sforce.ws.util.Base64;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.format.ISODateTimeFormat;
import uk.co.virtual1.salesforce.object.Access;
import uk.co.virtual1.salesforce.object.Account;
import uk.co.virtual1.salesforce.object.AccountPbt;
import uk.co.virtual1.salesforce.object.AnalogueLine;
import uk.co.virtual1.salesforce.object.Asset;
import uk.co.virtual1.salesforce.object.BaseSalesforceObject;
import uk.co.virtual1.salesforce.object.Case;
import uk.co.virtual1.salesforce.object.CaseComment;
import uk.co.virtual1.salesforce.object.CaseContactRole;
import uk.co.virtual1.salesforce.object.ChargeType;
import uk.co.virtual1.salesforce.object.CloudProvisioning;
import uk.co.virtual1.salesforce.object.Component;
import uk.co.virtual1.salesforce.object.Contact;
import uk.co.virtual1.salesforce.object.EndCustomer;
import uk.co.virtual1.salesforce.object.Exchange;
import uk.co.virtual1.salesforce.object.FeedItem;
import uk.co.virtual1.salesforce.object.InnerVLAN;
import uk.co.virtual1.salesforce.object.NNI;
import uk.co.virtual1.salesforce.object.NetOpsCase;
import uk.co.virtual1.salesforce.object.Opportunity;
import uk.co.virtual1.salesforce.object.PricingEntry;
import uk.co.virtual1.salesforce.object.Project;
import uk.co.virtual1.salesforce.object.Radius;
import uk.co.virtual1.salesforce.object.RecordType;
import uk.co.virtual1.salesforce.object.SFIPJustDevice;
import uk.co.virtual1.salesforce.object.SfAttachment;
import uk.co.virtual1.salesforce.object.SfEmail;
import uk.co.virtual1.salesforce.object.SfIpJust;
import uk.co.virtual1.salesforce.object.Site;
import uk.co.virtual1.salesforce.object.VLAN;
import uk.co.virtual1.salesforce.object.VPN;
import uk.co.virtual1.salesforce.object.VPNItem;
import uk.co.virtual1.salesforce.object.Virtual1DatacentrePostcode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


class ConvertingSFObjects {

    Account convertAccount(XmlObject sObject) {
        Account account = prepareObject(new Account(), sObject);

        account.setOneViewUsername((String) sObject.getField("PRTG_Username__c"));
        account.setOneViewPassword((String) sObject.getField("PRTG_Password__c"));
        account.setPoNumberRequired(BooleanUtils.toBoolean((String) sObject.getField("PO_Number_Required__c")));

        account.setBillingStreet((String) sObject.getField("BillingStreet"));
        account.setBillingCity((String) sObject.getField("BillingCity"));
        account.setBillingState((String) sObject.getField("BillingState"));
        account.setBillingPostalCode((String) sObject.getField("BillingPostalCode"));
        account.setBillingCountry((String) sObject.getField("BillingCountry"));

        account.setShippingStreet((String) sObject.getField("ShippingStreet"));
        account.setShippingCity((String) sObject.getField("ShippingCity"));
        account.setShippingState((String) sObject.getField("ShippingState"));
        account.setShippingPostalCode((String) sObject.getField("ShippingPostalCode"));
        account.setShippingCountry((String) sObject.getField("ShippingCountry"));
        account.setCompanyName((String) sObject.getField("Registered_Name__c"));
        account.setCoReg((String) sObject.getField("Co_Reg__c"));
        account.setCoeApproved((String) sObject.getChild("COE_Approved__c").getValue());
        account.setRidNumber((String) sObject.getChild("RID_Number__c").getValue());
        String x1CloudDemoRequestBlackoutStr = (String) sObject.getChild("X1Cloud_Demo_Request_Blackout__c").getValue();
        if (x1CloudDemoRequestBlackoutStr != null) {
            account.setX1CloudDemoRequestBlackout(ISODateTimeFormat.dateTimeParser().parseDateTime(x1CloudDemoRequestBlackoutStr).toDate());
        }
        account.setStatus((String) sObject.getField("Status__c"));
        account.setPbt((String) sObject.getField("PBT__c"));

        XmlObject child = sObject.getChild("Owner");
        if (child != null) {
            account.setAccountOwnerId((String) child.getField("Id"));
            account.setAccountOwnerName((String) child.getField("Name"));
            account.setAccountOwnerEmail((String) child.getField("Email"));
            account.setAccountOwnerMobileNumber((String) child.getField("MobilePhone"));
        }

        return account;
    }

    SObject convertForSpecialInstructionsUpdate(Account account) {
        SObject sObject = new SObject();
        sObject.setType("Account");
        sObject.setId(account.getId());
        sObject.setField("Sales_Order_Special_Instructions__c", account.getSalesOrderSpecialInstructions());
        sObject.setField("RID_Number__c", account.getRidNumber());
        return sObject;
    }

    SObject convertForBillingAddressUpdate(Account account) {
        SObject sObject = new SObject();
        sObject.setType("Account");
        sObject.setId(account.getId());

        sObject.setField("ShippingStreet", account.getShippingStreet());
        sObject.setField("ShippingCity", account.getShippingCity());
        sObject.setField("ShippingState", account.getShippingState());
        sObject.setField("ShippingPostalCode", account.getShippingPostalCode());
        sObject.setField("ShippingCountry", account.getShippingCountry());
        return sObject;
    }


    AccountPbt convertAccountPBT(XmlObject sObject) {
        AccountPbt account = prepareObject(new AccountPbt(), sObject);
        account.setPbt((String) sObject.getField("PBT__c"));
        return account;
    }

    Contact convertContact(XmlObject sObject) {
        Contact contact = prepareObject(new Contact(), sObject);

        contact.setAccountId((String) sObject.getField("AccountId"));
        contact.setTelephone((String) sObject.getField("Phone"));
        contact.setEmail((String) sObject.getField("Email"));
        contact.setFirstName((String) sObject.getField("FirstName"));
        contact.setLastName((String) sObject.getField("LastName"));
        contact.setJobTitle((String) sObject.getField("Title"));
        contact.setDepartment((String) sObject.getField("Department"));
        contact.setRoles((String) sObject.getField("Role__c"));
        return contact;
    }

    protected SObject convert(Contact contact) {
        SObject sObject = new SObject();
        sObject.setType("Contact");
        String sfContactId = contact.getId();
        String contactName = contact.getName();
        if (StringUtils.isNotBlank(sfContactId)) {
            contactName = "";
            sObject.setId(sfContactId);
        }
        sObject.setField("AccountId", contact.getAccountId());

        sObject.setField("FirstName", QueryTemplates.escapeSOQL(contact.getFirstName()));
        sObject.setField("LastName", QueryTemplates.escapeSOQL(contact.getLastName()));
        sObject.setField("Email", QueryTemplates.escapeSOQL(contact.getEmail()));
        sObject.setField("Phone", contact.getTelephone());
        sObject.setField("Title", contact.getJobTitle());
        sObject.setField("Role__c", contact.getRoles());

        sObject.setField("X1Portal_User__c", contact.getX1PortalUSer());
        sObject.setField("HasOptedOutOfEmail", contact.getHasOptedOutOfEmail());
        sObject.setField("DoNotCall", contact.getDoNotCall());
        sObject.setField("Department", contact.getDepartment());
        sObject.setField("Name", contactName);
        sObject.setField("Title", contact.getJobTitle());

        return sObject;
    }

    protected SObject convert(AnalogueLine analogueLine) {
        SObject sObject = new SObject();

        sObject.setType("Analogue_Line__c");
        sObject.setField("Access_ID__c", analogueLine.getAccessID());
        sObject.setField("Annual_Rental_Cost__c", analogueLine.getAnnualRentalCost());
        sObject.setField("Carrier_Contract_mths__c", analogueLine.getCarrierContractMths());
        sObject.setField("Carrier_End_Date__c", analogueLine.getCarrierEndDate());
        sObject.setField("Carrier_Live_Date__c", analogueLine.getCarrierLiveDate());
        sObject.setField("Carrier_Product_Name__c", analogueLine.getCarrierProductName());
        sObject.setField("Carrier_Provider__c", analogueLine.getCarrierProvider());
        sObject.setField("End_Customer_Name__c", analogueLine.getEndCustomerName());
        sObject.setField("One_Off_Cost__c", analogueLine.getOneOffCost());
        sObject.setField("Project_Number__c", analogueLine.getProjectNumber());
        sObject.setField("Project_Status__c", analogueLine.getProjectStatus());
        sObject.setField("Site_Name__c", analogueLine.getSiteName());
        sObject.setField("Status__c", analogueLine.getStatus());

        return sObject;
    }

    NetOpsCase convertNetOpsCase(SObject sObject) {
        NetOpsCase netopsCase = new NetOpsCase();

        netopsCase.setId((String) sObject.getField("Id"));
        netopsCase.setAccessName((String) sObject.getField("Access_ID_Name__c"));
        netopsCase.setAccessType((String) sObject.getField("Access_Type__c"));
        netopsCase.setServiceType(getSubField(sObject, "Access_ID__r.Service_Type__c"));
        netopsCase.setEndCustomerName((String) sObject.getField("End_Customer_Name__c"));
        netopsCase.setSiteName((String) sObject.getField("Site_Name_B_End__c"));
        netopsCase.setNetOpsOwnerName(getSubField(sObject, "NetOps_Owner__r.Name"));
        netopsCase.setRfsDate(getDate(sObject, "Provisional_RFS__c"));
        netopsCase.setCircuitOrderedDate(getDate(sObject, "Ordered_with_Carrier_Date__c"));
        netopsCase.setSurveyDate(getDate(sObject, "Site_Survey_Date__c"));
        netopsCase.setSurveyResult((String) sObject.getField("Site_Survey_Results__c"));
        netopsCase.setInstallationDate(getDate(sObject, "Installation_Date__c"));
        netopsCase.setAssetName(getSubField(sObject, "Asset_ID_B_End__r.Name"));

        String poNumber = getSubField(sObject, "Asset_ID_B_End__r.PO_Number_del__c");
        netopsCase.setRouterOrdered(StringUtils.isNotBlank(poNumber));

        String loopbackAddress = getSubField(sObject, "Asset_ID_B_End__r.Loop_Back_Address__c");
        netopsCase.setLoopBackAssigned(StringUtils.isNotBlank(loopbackAddress));

        String assetStatus = getSubField(sObject, "Asset_ID_B_End__r.Status__c");
        netopsCase.setRouterShipped("Shipped".equals(assetStatus) || "Active".equals(assetStatus));

        return netopsCase;
    }

    private CaseContactRole convertCaseContactRole(XmlObject sObject) {
        CaseContactRole caseContactRole = prepareObject(new CaseContactRole(), sObject);
        caseContactRole.setCaseId((String) sObject.getField("CasesId"));
        caseContactRole.setIsDeleted((String) sObject.getField("IsDeleted"));

        XmlObject contactObj = sObject.getChild("Contact");
        if (isNotEmpty(contactObj)) {
            Contact sfContact = convertContact(contactObj);
            caseContactRole.setContact(sfContact);
        }
        return caseContactRole;
    }

    Access convertAccess(XmlObject sObject) {
        Access access = prepareObject(new Access(), sObject);

        XmlObject accessRecordType = sObject.getChild("RecordType");
        if (isNotEmpty(accessRecordType)) {
            access.setRecordType((String) accessRecordType.getField("Name"));
        }

        String contractEndDate = (String) sObject.getField("Service_End_Date__c");
        if (contractEndDate != null && contractEndDate.length() > 0) {
            access.setContractEndDate(ISODateTimeFormat.dateTimeParser().parseDateTime(contractEndDate).toDate());
        }

        access.setEndCustomerName((String) sObject.getField("End_Customer_Name__c"));

        XmlObject siteBObj = sObject.getChild("Site_Name_B_End__r");
        if (isNotEmpty(siteBObj)) {
            Site site = convertSite(siteBObj);
            access.setSiteBEnd(site);
        }

        XmlObject siteAObj = sObject.getChild("Site_Name_A_End__r");
        if (isNotEmpty(siteAObj)) {
            Site site = convertSite(siteAObj);
            access.setSiteAEnd(site);
        }

        access.setAccessType((String) sObject.getField("Access_Type__c"));
        access.setServiceType((String) sObject.getField("Service_Type__c"));
        access.setServiceId((String) sObject.getField("Carrier_Service_ID__c"));

        access.setProviderId((String) sObject.getField("Carrier_Provider__c"));
        XmlObject provider = sObject.getChild("Carrier_Provider__r");
        if (isNotEmpty(provider)) {
            access.setProvider((String) provider.getField("Name"));
        }

        access.setFloorBEnd((String) sObject.getField("Floor_B_End__c"));
        access.setRoomBEnd((String) sObject.getField("Room_B_End__c"));
        access.setRackBEnd((String) sObject.getField("Rack_B_End__c"));

        access.setStatus((String) sObject.getField("Status__c"));
        access.setTelephone((String) sObject.getField("ADSL_Telephone__c"));
        access.setBandwidthSold((String) sObject.getField("Bandwidth_Sold__c"));
        access.setBearerSpeed((String) sObject.getField("Bearer_Speed__c"));
        access.setRelation((String) sObject.getField("Relation__c"));
        access.setAssetBEndName(getSubField(sObject, "Asset_ID_B_End__r.Name"));
        access.setCarrierInterfaceBEnd((String) sObject.getField("Carrier_Interface_B_End__c"));
        access.setProviderProductName((String) sObject.getField("Carrier_Product_Name_new__c"));

        access.setCarrierContract(convertToInteger(sObject.getField("Carrier_Contract_months__c")));


        String inServiceDate = (String) sObject.getField("In_Service_Date__c");
        if (inServiceDate != null && inServiceDate.length() > 0) {
            access.setInServiceDate(ISODateTimeFormat.dateTimeParser().parseDateTime(inServiceDate).toDate());
        }

        //VLANS & Radius

        for (Iterator<XmlObject> iterator = sObject.getChildren("VLANS__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objVlan = iterator2.next();
                VLAN vlan = convertVLAN(objVlan);
                access.getVlans().add(vlan);

                if (access.getCarrierNNIId() == null) {
                    XmlObject nni__r = objVlan.getChild("NNI__r");
                    String carrierNNIId = (String) nni__r.getField("Carrier_NNI_ID__c");
                    if (StringUtils.isNotBlank(carrierNNIId)) {
                        access.setCarrierNNIId(carrierNNIId);
                    }
                }
            }
        }

        for (Iterator<XmlObject> iterator = sObject.getChildren("International_VLANS__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objVlan = iterator2.next();
                VLAN vlan = convertVLAN(objVlan);
                access.getInternationalVlans().add(vlan);
            }
        }

        for (Iterator<XmlObject> iterator = sObject.getChildren("Radius__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objRadius = iterator2.next();
                Radius radius = convertRadius(objRadius);
                access.getRadiuses().add(radius);
            }
        }

        List<PricingEntry> pricingEntries = retrievePricingEntries(sObject.getChildren("Pricing_Entries__r"));
        access.setPricingEntryList(pricingEntries);

        return access;
    }

    private VLAN convertVLAN(XmlObject sObject) {
        VLAN vlan = prepareObject(new VLAN(), sObject);

        vlan.setCIR((String) sObject.getField("CIR__c"));
        vlan.setLANIPv4Gateway((String) sObject.getField("LAN_IPv4_Gateway__c"));
        vlan.setRelatedLANPort((String) sObject.getField("Related_LAN_Port__c"));

        XmlObject recordType = sObject.getChild("RecordType");
        if (isNotEmpty(recordType)) {
            vlan.setRecordType((String) recordType.getField("Name"));
        }

        XmlObject vpn__r = sObject.getChild("VPN__r");
        if (isNotEmpty(vpn__r)) {
            vlan.setVpnName((String) vpn__r.getField("Name"));
            vlan.setVpnType((String) vpn__r.getField("VPN_Type__c"));
        }

        XmlObject intl_VPN__r = sObject.getChild("Intl_VPN__r");
        if (isNotEmpty(intl_VPN__r)) {
            vlan.setVpnName((String) intl_VPN__r.getField("Name"));
            vlan.setVpnType((String) intl_VPN__r.getField("VPN_Type__c"));
        }

        return vlan;
    }

    InnerVLAN convertInnerVLAN(XmlObject sObject) {
        InnerVLAN vlan = prepareObject(new InnerVLAN(), sObject);

        vlan.setAccessName((String) sObject.getField("Access_ID__c"));
        vlan.setCIR((String) sObject.getField("CIR_Mb__c"));
        vlan.setLANIPv4Gateway((String) sObject.getField("LAN_IPv4_Gateway__c"));
        vlan.setRelatedLANPort((String) sObject.getField("Related_LAN_Port__c"));

        XmlObject recordType = sObject.getChild("RecordType");
        if (isNotEmpty(recordType)) {
            vlan.setRecordType((String) recordType.getField("Name"));
        }

        vlan.setOuterVLANName(getSubField(sObject, "Outer_VLAN_Number__r.Name"));
        XmlObject vpn__r = sObject.getChild("VPN__r");
        if (isNotEmpty(vpn__r)) {
            vlan.setVpnName((String) vpn__r.getField("Name"));
            vlan.setVpnType((String) vpn__r.getField("VPN_Type__c"));
        }

        return vlan;
    }

    private Radius convertRadius(XmlObject objRadius) {
        Radius radius = prepareObject(new Radius(), objRadius);

        radius.setLANIPv4Gateway((String) objRadius.getField("LAN_IPv4_Gateway__c"));
        radius.setUsername((String) objRadius.getField("Username__c"));
        radius.setPassword((String) objRadius.getField("Password__c"));

        XmlObject vpn__r = objRadius.getChild("VPN__r");
        if (isNotEmpty(vpn__r)) {
            radius.setVpnName((String) vpn__r.getField("Name"));
            radius.setVpnType((String) vpn__r.getField("VPN_Type__c"));
        }
        return radius;
    }


    Project convertProject(SObject sObject) {
        Project project = prepareObject(new Project(), sObject);

        XmlObject accountObj = sObject.getChild("Account_Name__r");
        if (isNotEmpty(accountObj)) {
            Account account = convertAccount(accountObj);
            project.setAccount(account);
        }

        XmlObject endCustomerObj = sObject.getChild("End_Customer_Name__r");
        if (isNotEmpty(endCustomerObj)) {
            EndCustomer endCustomer = convertEndCustomer(endCustomerObj);
            project.setEndCustomer(endCustomer);
        }

        XmlObject contactObj = sObject.getChild("Technical_Contact_Name__r");
        if (isNotEmpty(contactObj)) {
            Contact contact = convertContact(contactObj);
            project.setContact(contact);
        }

        project.setProjectType(getSubField(sObject, "Project_Type__c"));
        project.setProjectStatus(getSubField(sObject, "Project_Status_del__c"));
        project.setProjectMgmtAllocated(BooleanUtils.toBoolean((String) sObject.getField("Project_Mgmt_Allocated__c")));
        project.setSpecialInstructions((String) sObject.getField("Special_Instructions__c"));
        project.setProjectNumber((String) sObject.getField("Project_Number__c"));

        project.setProjectManager(getSubField(sObject, "Project_Manager__r.Name"));
        project.setNetOpsOwner(getSubField(sObject, "NetOps_Owner__r.Name"));
        project.setProvisioningOwner(getSubField(sObject, "Owner.Name"));


        for (Iterator<XmlObject> iterator = sObject.getChildren("Assets__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objChild = iterator2.next();
                project.getAssets().add(convertAsset(objChild));
            }
        }

        for (Iterator<XmlObject> iterator = sObject.getChildren("Access__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objChild = iterator2.next();
                project.getAccesses().add(convertAccess(objChild));
            }
        }

        for (Iterator<XmlObject> iterator = sObject.getChildren("Components__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objChild = iterator2.next();
                project.getComponents().add(convertComponent(objChild));
            }
        }

        for (Iterator<XmlObject> iterator = sObject.getChildren("Cases__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objChild = iterator2.next();
                project.getCases().add(convertCase(objChild));
            }
        }

        return project;
    }

    private Component convertComponent(XmlObject objChild) {
        Component component = new Component();

        component.setId((String) objChild.getField("Id"));
        component.setName((String) objChild.getField("Name"));
        component.setSiteName((String) objChild.getField("Site_Name__c"));
        component.setCategory((String) objChild.getField("Component_Category__c"));
        component.setType((String) objChild.getField("Component_Type_New__c"));
        component.setStatus((String) objChild.getField("Status__c"));
        component.setAssetName(getSubField(objChild, "Asset_ID__r.Name"));
        return component;
    }

    // TODO: 16.08.16  
    VPN convertVPN(SObject sObject) {
        VPN vpn = prepareObject(new VPN(), sObject);

        vpn.setVpnType((String) sObject.getField("VPN_Type__c"));
        vpn.setNetworkDiagram((String) sObject.getField("Network_Diagram__c"));

        XmlObject endCustomerObj = sObject.getChild("End_Customer__r");
        if (isNotEmpty(endCustomerObj)) {
            EndCustomer endCustomer = convertEndCustomer(endCustomerObj);
            vpn.setEndCustomer(endCustomer);
        }

        XmlObject recordTypeObj = sObject.getChild("RecordType");
        if (isNotEmpty(recordTypeObj)) {
            vpn.setRecordType((String) recordTypeObj.getField("Name"));
        }

        for (Iterator<XmlObject> iterator = sObject.getChildren("VLANS__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();

            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objVlan = iterator2.next();

                VPNItem vlan = new VPNItem();
                vlan.setId((String) objVlan.getField("Id"));
                vlan.setName((String) objVlan.getField("Name"));
                vlan.setCIR((String) objVlan.getField("CIR__c"));
                vlan.setRecordType(getSubField(objVlan, "RecordType.Name"));
                vlan.setAccessName(getSubField(objVlan, "Access_ID__r.Name"));
                vlan.setSiteName(getSubField(objVlan, "Access_ID__r.Site_Name_B_End__r.Name"));
                vlan.setAccessType(getSubField(objVlan, "Access_ID__r.Access_Type__c"));
                vlan.setRelation(getSubField(objVlan, "Access_ID__r.Relation__c"));
                vlan.setAccessStatus(getSubField(objVlan, "Access_ID__r.Status__c"));

                String customerId = getSubField(objVlan, "Access_ID__r.Site_Name_B_End__r.End_Customer_Name__r.Id");
                vlan.setEndCustomerId(customerId);
                vlan.setEndCustomerName((String) objVlan.getField("End_Customer_Name__c"));

                vpn.getVlans().add(vlan);
            }
        }


        for (Iterator<XmlObject> iterator = sObject.getChildren("Inner_VLANs__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();

            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objVlan = iterator2.next();


                VPNItem vlan = new VPNItem();
                vlan.setId((String) objVlan.getField("Id"));
                vlan.setName((String) objVlan.getField("Name"));
                vlan.setCIR((String) objVlan.getField("CIR_Mb__c"));
                vlan.setRecordType(getSubField(objVlan, "RecordType.Name"));
                vlan.setAccessName(getSubField(objVlan, "Outer_VLAN_Number__r.Access_ID__r.Name"));
                vlan.setSiteName(getSubField(objVlan, "Outer_VLAN_Number__r.Access_ID__r.Site_Name_B_End__r.Name"));
                vlan.setAccessType(getSubField(objVlan, "Outer_VLAN_Number__r.Access_ID__r.Access_Type__c"));
                vlan.setRelation(getSubField(objVlan, "Outer_VLAN_Number__r.Access_ID__r.Relation__c"));
                vlan.setAccessStatus(getSubField(objVlan, "Outer_VLAN_Number__r.Access_ID__r.Status__c"));

                String customerId = getSubField(objVlan, "Outer_VLAN_Number__r.Access_ID__r.Site_Name_B_End__r.End_Customer_Name__r.Id");
                vlan.setEndCustomerId(customerId);
                vlan.setEndCustomerName((String) objVlan.getField("End_Customer_Name__c"));

                vpn.getVlans().add(vlan);
            }
        }

        for (Iterator<XmlObject> iterator = sObject.getChildren("Radius__r"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();

            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject objRadius = iterator2.next();

                VPNItem radius = new VPNItem();
                radius.setId((String) objRadius.getField("Id"));
                radius.setName((String) objRadius.getField("Name"));
                radius.setAccessName(getSubField(objRadius, "Access_ID__r.Name"));
                radius.setSiteName(getSubField(objRadius, "Access_ID__r.Site_Name_B_End__r.Name"));
                radius.setAccessType(getSubField(objRadius, "Access_ID__r.Access_Type__c"));
                radius.setRelation(getSubField(objRadius, "Access_ID__r.Relation__c"));
                radius.setAccessStatus(getSubField(objRadius, "Access_ID__r.Status__c"));

                radius.setEndCustomerId(getSubField(objRadius, "Access_ID__r.Site_Name_B_End__r.End_Customer_Name__r.Id"));
                radius.setEndCustomerName((String) objRadius.getField("End_Customer_Name__c"));

                vpn.getRadiuses().add(radius);
            }
        }

        return vpn;
    }

    Virtual1DatacentrePostcode convertVirtul1DatacentrePostcode(XmlObject sObject) {
        Virtual1DatacentrePostcode datacentrePostcode = prepareObject(new Virtual1DatacentrePostcode(), sObject);
        datacentrePostcode.setPostcode((String) sObject.getField("Postcode__c"));
        datacentrePostcode.setExchangeName((String) sObject.getField("Virtual1_Exchange_Name__c"));
        return datacentrePostcode;
    }

    AnalogueLine convertAnalogueLine(XmlObject sObject) {
        AnalogueLine analogueLine = new AnalogueLine();
        analogueLine.setId((String) sObject.getField("Id"));
        analogueLine.setName((String) sObject.getField("Name"));
        analogueLine.setCallBarring(BooleanUtils.toBoolean((String) sObject.getField("Call_Barring__c")));
        analogueLine.setStatus((String) sObject.getField("Status__c"));
        return analogueLine;
    }

    Asset convertAsset(XmlObject sObject) {
        Asset asset = prepareObject(new Asset(), sObject);

        XmlObject recordType = sObject.getChild("RecordType");
        if (isNotEmpty(recordType)) {
            asset.setRecordType((String) recordType.getField("Name"));
        }

        asset.setInstanceKey((String) sObject.getField("MonitoringInstance__r.Name"));
        asset.setOneViewId((String) sObject.getField("MonitoringID__c"));
        asset.setCpeName((String) sObject.getField("CPE_Name__c"));
        asset.setCategory((String) sObject.getField("Asset_Category__c"));
        asset.setContractEndDate(getDate(sObject, "Service_End_Date__c"));
        asset.setInServiceDate(getDate(sObject, "In_Service_Date__c"));
        asset.setAssetType((String) sObject.getField("Asset_Type_New__c"));
        asset.setAssetManaged((String) sObject.getField("Asset_Managed__c"));
        asset.setStatus((String) sObject.getField("Status__c"));
        asset.setShippedDate(getDate(sObject, "Shipped_Date__c"));
        asset.setEnterpriseName((String) sObject.getField("Enterprise_Name__c"));
        asset.setRackSize((String) sObject.getField("Rack_Size__c"));
        asset.setPowerRatings((String) sObject.getField("Power_Ratings__c"));

        XmlObject siteObj = sObject.getChild("Site_Name__r");
        if (isNotEmpty(siteObj)) {
            Site site = convertSite(siteObj);
            asset.setSite(site);
        }

        List<PricingEntry> pricingEntries = retrievePricingEntries(sObject.getChildren("Pricing_Entries__r"));
        asset.setPricingEntryList(pricingEntries);

        return asset;
    }

    private List<PricingEntry> retrievePricingEntries(Iterator<XmlObject> iterator) {
        List<PricingEntry> result = new ArrayList<>();
        for (; iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject prEntry = iterator2.next();
                PricingEntry pricingEntry = convertPricingEntry(prEntry);
                result.add(pricingEntry);
            }
        }
        return result;
    }

    protected SObject convert(VPN vpn, SalesforceService salesforceService) {
        SObject sfObject = new SObject();
        sfObject.setType("VPN__c");

        String recordType = salesforceService.getRecordTypeId("VPN__c", VPN.RECORD_TYPE_VPN);
        sfObject.setField("RecordTypeId", recordType);

        sfObject.setField("Name", vpn.getName());
        sfObject.setField("End_Customer__c", vpn.getEndCustomerId());
        sfObject.setField("Network_Diagram__c", vpn.getNetworkDiagram());
        sfObject.setField("Route_Distinguisher__c", vpn.getRouteDistinguisher());
        sfObject.setField("VPN_Description__c", vpn.getVpnDescription());
        sfObject.setField("VPN_Type__c", vpn.getVpnType());
        return sfObject;
    }

    protected SObject convert(Opportunity opportunity) {

        SObject sfObject = new SObject();
        sfObject.setType("Opportunity");

        sfObject.setField("OwnerId", opportunity.getOpportunityOwner());
        sfObject.setField("AccountId", opportunity.getAccountId());
        sfObject.setField("Name", opportunity.getOpportunityName());
        sfObject.setField("Type", opportunity.getOpportunityType());
        sfObject.setField("Solution_Type__c", opportunity.getSolutionType());

        sfObject.setField("Opportunity_Source__c", opportunity.getOpportunitySource());

        sfObject.setField("Service_Term_months__c", opportunity.getServiceTerm());
        sfObject.setField("No_of_Sites__c", opportunity.getNumberOfSites());
        sfObject.setField("BDUK_Opportunity__c", opportunity.isBdukOpportunity());

        sfObject.setField("PM_Required__c", opportunity.isPmRequired());
        sfObject.setField("No_PM_days_Quoted__c", opportunity.getNumberPmDaysQuoted());
        sfObject.setField("No_PM_Authorised__c", opportunity.getNumberPmAuthorised());

        sfObject.setField("Related_Project__c", opportunity.getRelatedProject());
        sfObject.setField("StageName", opportunity.getStage());
        sfObject.setField("ForecastCategoryName", opportunity.getForecastCategory());
        sfObject.setField("Probability", opportunity.getProbability());
        sfObject.setField("CloseDate", opportunity.getCloseDate());
        sfObject.setField("One_Off__c", opportunity.getOneOfCharges());
        sfObject.setField("Annual_Rental__c", opportunity.getAnnualRental());
        sfObject.setField("OneOffCost__c", opportunity.getOneOffCost());
        sfObject.setField("AnnualSupplierCost__c", opportunity.getAnnualSupplierCost());

        sfObject.setField("X1Portal_Quote_ID__c", opportunity.getX1PortalQuoteID());

        if (opportunity.getTalkTalkEthernetForecast() != null) {
            sfObject.setField("TalkTalk_Ethernet__c", opportunity.getTalkTalkEthernetForecast());
        }
        if (opportunity.getTalkTalkEFMForecast() != null) {
            sfObject.setField("TalkTalk_EFM__c", opportunity.getTalkTalkEFMForecast());
        }
        if (opportunity.getVirginEthernetForecast() != null) {
            sfObject.setField("Virgin_Ethernet__c", opportunity.getVirginEthernetForecast());
        }
        if (opportunity.getbTWEthernetForecast() != null) {
            sfObject.setField("BTW_Ethernet__c", opportunity.getbTWEthernetForecast());
        }
        if (opportunity.getbTWEFMForecast() != null) {
            sfObject.setField("BTW_EFM__c", opportunity.getbTWEFMForecast());
        }
        if (opportunity.getbTWFTTCForecast() != null) {
            sfObject.setField("BTW_FTTC__c", opportunity.getbTWFTTCForecast());
        }
        if (opportunity.getColtEthernetForecast() != null) {
            sfObject.setField("Colt_Ethernet__c", opportunity.getColtEthernetForecast());
        }
        if (opportunity.getVodafoneEthernetForecast() != null) {
            sfObject.setField("Vodafone_Ethernet__c", opportunity.getVodafoneEthernetForecast());
        }
        if (opportunity.getgS2RacksForecast() != null) {
            sfObject.setField("GS2_Racks__c", opportunity.getgS2RacksForecast());
        }
        if (opportunity.getsSEEthernetForecast() != null) {
            sfObject.setField("SSE_Ethernet__c", opportunity.getsSEEthernetForecast());
        }
        if (opportunity.getEuNetworksEthernetForecast() != null) {
            sfObject.setField("EU_Networks_Ethernet__c", opportunity.getEuNetworksEthernetForecast());
        }
        if (opportunity.getVirtual1FibreForecast() != null) {
            sfObject.setField("Virtual1_Fibre__c", opportunity.getVirtual1FibreForecast());
        }
        if (opportunity.getVirtual1FTTCForecast() != null) {
            sfObject.setField("Virtual1_FTTC__c", opportunity.getVirtual1FTTCForecast());
        }
        if (opportunity.getOtherCarrierForecast() != null) {
            sfObject.setField("Other_Carrier__c", opportunity.getOtherCarrierForecast());
        }
        if (opportunity.getLhrRacksForecast() != null) {
            sfObject.setField("LHR_Racks__c", opportunity.getLhrRacksForecast());
        }
        if (opportunity.getU1OfFirewallHostingForecast() != null) {
            sfObject.setField("X1u_of_Firewall_Hosting__c", opportunity.getU1OfFirewallHostingForecast());
        }

        for (Map.Entry<String, Object> entry : opportunity.getCustomFields().entrySet()) {
            sfObject.setField(entry.getKey(), entry.getValue());
        }

        return sfObject;
    }


    protected SObject convert(Case sfcase) {
        SObject caseObject = new SObject();
        caseObject.setType("Case");

        if (StringUtils.isNotBlank(sfcase.getId())) { // update
            caseObject.setId(sfcase.getId());
        } else {
            caseObject.setField("AccountId", sfcase.getAccountId());
        }

        if (StringUtils.isNotBlank(sfcase.getContact().getId())) {
            caseObject.setField("ContactId", sfcase.getContact().getId());
        }

        if (StringUtils.isNotBlank(sfcase.getSubject())) {
            caseObject.setField("Subject", sfcase.getSubject());
        }

        caseObject.setField("Reason", sfcase.getReason());
        caseObject.setField("Status", sfcase.getStatus());
        caseObject.setField("Origin", sfcase.getOrigin());
        caseObject.setField("Type", sfcase.getType());
        caseObject.setField("RecordTypeId", sfcase.getRecordTypeId());
        caseObject.setField("Description", sfcase.getDescription());
        caseObject.setField("Partner_Internal_Ref__c", sfcase.getReference());

        caseObject.setField("Lead_Time_Range__c", sfcase.getLeadTime());
        caseObject.setField("Delivery_Schedule__c", sfcase.getDeliverySchedule());
        caseObject.setField("Order_Received_Date__c", sfcase.getOrderReceived());

        caseObject.setField("Site_Name_B_End__c", sfcase.getSiteName());
        caseObject.setField("Post_Code_B_End__c", sfcase.getPostCode());
        caseObject.setField("Address_B_End__c", sfcase.getAddress());
        caseObject.setField("Site_Access_Available_Date_B_End__c", sfcase.getAccessAvailableDateBEnd());
        caseObject.setField("Site_Constraints_B_End__c", sfcase.getSiteConstrainsBEnd());
        caseObject.setField("Site_Status_B_End__c", sfcase.getSiteStatusBEnd());
        caseObject.setField("Site_Notes_B_End__c", sfcase.getSiteNotes());

        caseObject.setField("carrier_appointment_reference__c", sfcase.getCarrierAppointmentReference());
        caseObject.setField("carrier_appointment_date__c", sfcase.getCarrierAppointmentDate());
        caseObject.setField("carrier_appointment_timeslot__c", sfcase.getCarrierAppointmentTimeslot());

        caseObject.setField("MinimumGuaranteedSpeed__c", sfcase.getMinimumGuaranteedSpeed());

        List<String> nullFields = new ArrayList<>();

        if (StringUtils.isNotBlank(sfcase.getAccess().getId())) {
            caseObject.setField("Access_ID__c", sfcase.getAccess().getId());
        } else { // empty
            nullFields.add("Access_ID__c");
        }

        if (StringUtils.isNotBlank(sfcase.getAssetAEnd().getId())) {
            caseObject.setField("Asset_ID_A_End__c", sfcase.getAssetAEnd().getId());
        } else { // empty
            nullFields.add("Asset_ID_A_End__c");
        }


        if (StringUtils.isNotBlank(sfcase.getAssetBEnd().getId())) {
            caseObject.setField("Asset_ID_B_End__c", sfcase.getAssetBEnd().getId());
        } else { // empty
            nullFields.add("Asset_ID_B_End__c");
        }

        if (nullFields.size() > 0) {
            caseObject.setFieldsToNull(nullFields.toArray(new String[]{}));
        }

        if (StringUtils.isNotBlank(sfcase.getPriority())) {
            caseObject.setField("Priority", sfcase.getPriority());
        } else {
            throw new RuntimeException("case-priority-is-blank");
        }

        for (Map.Entry<String, Object> entry : sfcase.getCustomFields().entrySet()) {
            caseObject.setField(entry.getKey(), entry.getValue());
        }

        return caseObject;
    }

    SObject convertForUpdate(Case sfcase) {
        SObject caseObject = new SObject();
        caseObject.setType("Case");

        if (StringUtils.isNotBlank(sfcase.getId())) { // update
            caseObject.setId(sfcase.getId());
        } else {
            caseObject.setField("AccountId", sfcase.getAccountId());
        }

        if (StringUtils.isNotBlank(sfcase.getContact().getId())) {
            caseObject.setField("ContactId", sfcase.getContact().getId());
        }

        if (StringUtils.isNotBlank(sfcase.getSubject())) {
            caseObject.setField("Subject", sfcase.getSubject());
        }

        caseObject.setField("Reason", sfcase.getReason());
        caseObject.setField("Status", sfcase.getStatus());
        caseObject.setField("Origin", sfcase.getOrigin());
        caseObject.setField("Type", sfcase.getType());
        caseObject.setField("RecordTypeId", sfcase.getRecordTypeId());
        caseObject.setField("Description", sfcase.getDescription());
        caseObject.setField("Partner_Internal_Ref__c", sfcase.getReference());

        caseObject.setField("Lead_Time_Range__c", sfcase.getLeadTime());
        caseObject.setField("Delivery_Schedule__c", sfcase.getDeliverySchedule());
        caseObject.setField("Order_Received_Date__c", sfcase.getOrderReceived());
//        caseObject.setField("Site_Name_B_End__c", sfcase.getSiteName());
//        caseObject.setField("Post_Code_B_End__c", sfcase.getPostCode());
//        caseObject.setField("Address_B_End__c", sfcase.getAddress());
        caseObject.setField("Site_Access_Available_Date_B_End__c", sfcase.getAccessAvailableDateBEnd());
        caseObject.setField("Site_Constraints_B_End__c", sfcase.getSiteConstrainsBEnd());
        caseObject.setField("Site_Status_B_End__c", sfcase.getSiteStatusBEnd());
        caseObject.setField("Site_Notes_B_End__c", sfcase.getSiteNotes());

        caseObject.setField("carrier_appointment_reference__c", sfcase.getCarrierAppointmentReference());
        caseObject.setField("carrier_appointment_date__c", sfcase.getCarrierAppointmentDate());
        caseObject.setField("carrier_appointment_timeslot__c", sfcase.getCarrierAppointmentTimeslot());

        List<String> nullFields = new ArrayList<>();

        if (StringUtils.isNotBlank(sfcase.getAccess().getId())) {
            caseObject.setField("Access_ID__c", sfcase.getAccess().getId());
        } else { // empty
            nullFields.add("Access_ID__c");
        }

        if (StringUtils.isNotBlank(sfcase.getAssetAEnd().getId())) {
            caseObject.setField("Asset_ID_A_End__c", sfcase.getAssetAEnd().getId());
        } else { // empty
            nullFields.add("Asset_ID_A_End__c");
        }


        if (StringUtils.isNotBlank(sfcase.getAssetBEnd().getId())) {
            caseObject.setField("Asset_ID_B_End__c", sfcase.getAssetBEnd().getId());
        } else { // empty
            nullFields.add("Asset_ID_B_End__c");
        }

        if (nullFields.size() > 0) {
            caseObject.setFieldsToNull(nullFields.toArray(new String[]{}));
        }

        if (StringUtils.isNotBlank(sfcase.getPriority())) {
            caseObject.setField("Priority", sfcase.getPriority());
        } else {
            throw new SalesforceException("case-priority-is-blank");
        }

        for (Map.Entry<String, Object> entry : sfcase.getCustomFields().entrySet()) {
            caseObject.setField(entry.getKey(), entry.getValue());
        }

        return caseObject;
    }

    public SObject convert(Radius radius) {
        SObject sfObject = new SObject();
        if (StringUtils.isNotBlank(radius.getId())) {
            sfObject.setId(radius.getId());
        }

        sfObject.setType("Radius__c");
        sfObject.setField("Access_ID__c", radius.getAccessId());
        sfObject.setField("Username__c", radius.getUsername());
        sfObject.setField("Password__c", radius.getPassword());
        sfObject.setField("NNI__c", radius.getNNI__c());
        sfObject.setField("VPN__c", radius.getVPN__c());

        return sfObject;
    }

    public SObject convert(CaseComment caseComment) {
        SObject sObject = new SObject();
        sObject.setType("CaseComment");
        sObject.setField("ParentId", caseComment.getCaseId());
        sObject.setField("CommentBody", caseComment.getBody());
        sObject.setField("IsPublished", caseComment.isPublished());
        return sObject;
    }

    public SObject convert(CaseContactRole caseContactRole) {
        SObject sObject = new SObject();
        sObject.setType("CaseContactRole");
        sObject.setField("CasesId", caseContactRole.getCaseId());
        sObject.setField("ContactId", caseContactRole.getContact().getId());
        sObject.setField("Role", caseContactRole.getRole());
        return sObject;
    }

    public SObject convert(ChargeType chargeType) {
        SObject sObject = new SObject();
        sObject.setType("Charge_Type__c");
        sObject.setField("Name", chargeType.getName());
        return sObject;
    }

    public ChargeType convertChargeType(XmlObject sObject) {
        return prepareObject(new ChargeType(), sObject);
    }

    public SObject convert(Access access) {

        SObject sfObject = new SObject();
        if (StringUtils.isNotBlank(access.getId())) {
            sfObject.setId(access.getId());
        }
        sfObject.setType("Access__c");
        sfObject.setField("RecordTypeId", access.getRecordTypeId());
        sfObject.setField("Project_Number__c", access.getProjectId());
        sfObject.setField("Status__c", access.getStatus());
        sfObject.setField("Relation__c", access.getRelation());
        sfObject.setField("Service_Type__c", access.getServiceType());
        sfObject.setField("Access_Type__c", access.getAccessType());
        sfObject.setField("Bandwidth_Sold__c", access.getBandwidthSold());
        sfObject.setField("Bearer_Speed__c", access.getBearerSpeed());
        sfObject.setField("Service_Term_months__c", access.getServiceTerms());
        sfObject.setField("Site_Name_B_End__c", access.getSiteId());
        sfObject.setField("Floor_B_End__c", access.getFloorBEnd());
        sfObject.setField("Room_B_End__c", access.getRoomBEnd());
        sfObject.setField("Rack_B_End__c", access.getRackBEnd());
        sfObject.setField("Interface_Type_B_End__c", access.getInterfaceType());
        sfObject.setField("Asset_ID_B_End__c", access.getAssetBEndId());
        sfObject.setField("Carrier_Provider__c", access.getProviderId());
        sfObject.setField("Carrier_Service_ID__c", access.getServiceId());
        sfObject.setField("Carrier_Product_Name_new__c", access.getProviderProductName());
        sfObject.setField("Serving_Exchange_Code__c", access.getServingExchangeCodeId());
        sfObject.setField("Carrier_CIR__c", access.getCarrierCIR());
        sfObject.setField("Carrier_Contract_months__c", access.getCarrierContract());
        sfObject.setField("Carrier_Interface_B_End__c", access.getCarrierInterfaceBEnd());
        sfObject.setField("No_of_Pairs_Delivered__c", access.getAccessDistancePairs());

        for (Map.Entry<String, Object> entry : access.getCustomFields().entrySet()) {
            sfObject.setField(entry.getKey(), entry.getValue());
        }

        return sfObject;
    }

    public SObject convert(Asset asset) {

        SObject sfObject = new SObject();
        sfObject.setType("Assets__c");
        sfObject.setField("RecordTypeId", asset.getRecordTypeId());
        sfObject.setField("Asset_Category__c", asset.getCategory());
        sfObject.setField("Asset_Type_New__c", asset.getAssetType());
        sfObject.setField("Site_Name__c", asset.getSiteId());
        sfObject.setField("Project_Number__c", asset.getProjectId());
        sfObject.setField("Status__c", asset.getStatus());
        sfObject.setField("Service_Term_months__c", asset.getServiceTerms());
        sfObject.setField("Ship_to__c", asset.getShipTo());
        sfObject.setField("Shipping_Contact__c", asset.getShippingContact());
        sfObject.setField("Shipping_Address__c", asset.getShippingAddress());
        sfObject.setField("Virtual1_Engineer_Install_Rqd_del__c",
                asset.isVirtual1EngineerInstallRqd() ? "Yes" : "No");
        sfObject.setField("Config_Notes__c", asset.getConfigNotes());
        sfObject.setField("X1_Tagging_Method__c", asset.getX1TaggingMethod());

        sfObject.setField("X1_Autonegotiation__c", asset.getX1Autonegotiation());
        sfObject.setField("X1_Interface_Speed__c", asset.getX1InterfaceSpeed());
        sfObject.setField("X1_Duplex__c", asset.getX1Duplex());
        sfObject.setField("X1_Interface_Type__c", asset.getX1InterfaceType());
        sfObject.setField("DesignType__c", asset.getDesignType());

        for (Map.Entry<String, Object> entry : asset.getCustomFields().entrySet()) {
            sfObject.setField(entry.getKey(), entry.getValue());
        }

        return sfObject;
    }

    protected SObject convert(Component component) {
        SObject sfObject = new SObject();
        sfObject.setType("Component__c");
        sfObject.setField("RecordTypeId", component.getRecordTypeId());
        sfObject.setField("Component_Type_New__c", component.getType());
        sfObject.setField("Component_Category__c", component.getCategory());
        sfObject.setField("Service_Term_months__c", component.getServiceTerms());
        sfObject.setField("Asset_ID__c", component.getAssetId());
        sfObject.setField("Project_Number__c", component.getProjectId());
        sfObject.setField("Status__c", component.getStatus());

        for (Map.Entry<String, Object> entry : component.getCustomFields().entrySet()) {
            sfObject.setField(entry.getKey(), entry.getValue());
        }

        return sfObject;
    }


    protected SObject convert(Project project) {
        SObject sfObject = new SObject();
        sfObject.setType("Project__c");

        if (project.getId() != null) {
            sfObject.setId(project.getId());
        }

        sfObject.setField("Account_Name__c", project.getAccount().getId());
        sfObject.setField("Project_Type__c", project.getProjectType());
        sfObject.setField("Project_Status_del__c", project.getProjectStatus());
        sfObject.setField("Technical_Contact_Name__c", project.getContact().getId());
        sfObject.setField("Special_Instructions__c", project.getSpecialInstructions());
        sfObject.setField("End_Customer_Name__c", project.getEndCustomer().getId());

        sfObject.setField("Customer_Signed_By__c", project.getCustomerSignedBy());
        sfObject.setField("Customer_Signed_Date__c", project.getCustomerSignedDate());
        //sfObject.setField("Customer_Signed_Title__c", project.getCustomerSignedTitle());
        sfObject.setField("Portal_Order_Fully_Loaded__c", project.getPortalOrderFullyLoaded());

        for (Map.Entry<String, Object> entry : project.getCustomFields().entrySet()) {
            sfObject.setField(entry.getKey(), entry.getValue());
        }

        return sfObject;
    }

    protected SObject convert(PricingEntry pricingEntry) {
        SObject sObject = new SObject();

        sObject.setType(SalesforceConstants.PRICING_ENTRY_RECORD_TYPE);
        sObject.setField("RecordTypeId", pricingEntry.getRecordTypeId());

        for (Map.Entry<String, Object> entry : pricingEntry.getCustomFields().entrySet()) {
            sObject.setField(entry.getKey(), entry.getValue());
        }

        switch (pricingEntry.getRecordType()) {
            case SalesforceConstants.ACCESS_PRICING_ENTRY_TYPE:
                sObject.setField("Project_Number__c", pricingEntry.getProjectId());
                sObject.setField("Case_Number__c", pricingEntry.getCaseId());
                sObject.setField("Account_Name__c", pricingEntry.getAccountId());
                sObject.setField("Access_ID__c", pricingEntry.getAccessId());
                sObject.setField("Charge_Type__c", pricingEntry.getChargeTypeId());
                sObject.setField("Amount__c", toDouble(pricingEntry.getAmount()));
                sObject.setField("Billing_Frequency__c", pricingEntry.getBillingFrequency());
                sObject.setField("Partner_PO_No__c", pricingEntry.getPartnerPONumber());
                sObject.setField("Supplier_Cost__c", toDouble(pricingEntry.getSupplierCost()));
                sObject.setField("Supplier_Quote_Ref__c", pricingEntry.getSupplierQuoteRef());
                sObject.setField("BTW_ETHA_Cost__c", toDouble(pricingEntry.getBTWETHACost()));
                sObject.setField("BTW_ETHC_Cost__c", toDouble(pricingEntry.getBTWETHCCost()));
                sObject.setField("Commissionable_Margin__c", toDouble(pricingEntry.getMargin()));
                sObject.setField("Previous_Rental_Amount__c", toDouble(pricingEntry.getPreviousRentalAmount()));
                sObject.setField("Is_a_Virtual1_BDUK_voucher_applicable__c", pricingEntry.getIsAVirtual1BDUKVoucherApplicable());
                sObject.setField("BDUK_Voucher_Reference__c", pricingEntry.getBdukVoucherReference());
                return sObject;
            case SalesforceConstants.ASSET_PRICING_ENTRY_TYPE:
                sObject.setField("Project_Number__c", pricingEntry.getProjectId());
                sObject.setField("Case_Number__c", pricingEntry.getCaseId());
                sObject.setField("Account_Name__c", pricingEntry.getAccountId());
                sObject.setField("Asset_ID__c", pricingEntry.getAssetId());
                sObject.setField("Charge_Type__c", pricingEntry.getChargeTypeId());
                sObject.setField("Amount__c", toDouble(pricingEntry.getAmount()));
                sObject.setField("Billing_Frequency__c", pricingEntry.getBillingFrequency());
                sObject.setField("Partner_PO_No__c", pricingEntry.getPartnerPONumber());
                sObject.setField("Supplier_Cost__c", toDouble(pricingEntry.getSupplierCost()));
                sObject.setField("Commissionable_Margin__c", toDouble(pricingEntry.getMargin()));
                sObject.setField("Previous_Rental_Amount__c", toDouble(pricingEntry.getPreviousRentalAmount()));
                return sObject;
            case SalesforceConstants.COMPONENT_PRICING_ENTRY_TYPE:
                sObject.setField("Project_Number__c", pricingEntry.getProjectId());
                sObject.setField("Case_Number__c", pricingEntry.getCaseId());
                sObject.setField("Account_Name__c", pricingEntry.getAccountId());
                sObject.setField("Component_ID__c", pricingEntry.getComponentId());
                sObject.setField("Charge_Type__c", pricingEntry.getChargeTypeId());
                sObject.setField("Amount__c", toDouble(pricingEntry.getAmount()));
                sObject.setField("Billing_Frequency__c", pricingEntry.getBillingFrequency());
                sObject.setField("Partner_PO_No__c", pricingEntry.getPartnerPONumber());
                sObject.setField("Supplier_Cost__c", toDouble(pricingEntry.getSupplierCost()));
                sObject.setField("Commissionable_Margin__c", toDouble(pricingEntry.getMargin()));
                sObject.setField("Previous_Rental_Amount__c", toDouble(pricingEntry.getPreviousRentalAmount()));
                return sObject;
            default:
                throw new SalesforceException("Pricing entry type " + pricingEntry.getRecordType() + " is not supported");
        }
    }

    protected PricingEntry convert(SObject sfObject, String type) {
        sfObject.getType();
        PricingEntry pricingEntry = null;
        if ("access".equals(type)) {
            pricingEntry = new PricingEntry("Access Pricing Entry");
            pricingEntry.setAccessId((String) sfObject.getField("Access_ID__c"));
        } else if ("asset".equals(type)) {
            pricingEntry = new PricingEntry("Asset Pricing Entry");
            pricingEntry.setAssetId((String) sfObject.getField("Asset_ID__c"));
        }

        if (pricingEntry != null) {
            pricingEntry.setChargeTypeName((String) sfObject.getChild("Charge_Type__r").getField("Name"));
            pricingEntry.setBillingFrequency((String) sfObject.getField("Billing_Frequency__c"));
            BigDecimal amount = new BigDecimal((String) sfObject.getField("Amount__c"));
            pricingEntry.setAmount("Quarterly".equals(pricingEntry.getBillingFrequency()) ? amount.divide(new BigDecimal(4), 2, RoundingMode.HALF_UP) : amount);
            pricingEntry.setBillingStartDate((String) sfObject.getField("Billing_Start_Date__c"));
            pricingEntry.setBillingEndDate((String) sfObject.getField("Billing_End_Date__c"));

        }
        return pricingEntry;
    }

    private PricingEntry convertPricingEntry(XmlObject sfObject) {
        PricingEntry pricingEntry = prepareObject(new PricingEntry(), sfObject);

        pricingEntry.setChargeTypeName((String) sfObject.getChild("Charge_Type__r").getField("Name"));
        pricingEntry.setBillingFrequency((String) sfObject.getField("Billing_Frequency__c"));
        BigDecimal amount = new BigDecimal((String) sfObject.getField("Amount__c"));
        pricingEntry.setAmount("Quarterly".equals(pricingEntry.getBillingFrequency()) ? amount.divide(new BigDecimal(4), 2, RoundingMode.HALF_UP) : amount);
        pricingEntry.setBillingStartDate((String) sfObject.getField("Billing_Start_Date__c"));
        pricingEntry.setBillingEndDate((String) sfObject.getField("Billing_End_Date__c"));

        return pricingEntry;
    }

    RecordType convertRecordType(XmlObject sObject) {
        RecordType recordType = prepareObject(new RecordType(), sObject);
        recordType.setsObjectType((String) sObject.getField("SobjectType"));
        recordType.setDescription((String) sObject.getField("Description"));
        return recordType;
    }

    SfIpJust convertSfIpJust(SObject sObject) {
        SfIpJust sfIpJust = prepareObject(new SfIpJust(), sObject);
        sfIpJust.setProjectId((String) sObject.getField("ProjectId__c"));
        sfIpJust.setProjectNumber((String) sObject.getChild("ProjectId__r").getField("Project_Number__c"));
        sfIpJust.setStatus((String) sObject.getField("Status__c"));
        sfIpJust.setCompanyAddress((String) sObject.getField("CompanyAddress__c"));
        sfIpJust.setCompanyWebsite((String) sObject.getField("CompanyWebsite__c"));
        sfIpJust.setAdminContactName((String) sObject.getField("AdminContactName__c"));
        sfIpJust.setAdminContactPostalAddress((String) sObject.getField("AdminContactPostalAddress__c"));
        sfIpJust.setAdminContactPhone((String) sObject.getField("AdminContactPhone__c"));
        sfIpJust.setAdminContactEmailAddress((String) sObject.getField("AdminContactEmailAddress__c"));
        sfIpJust.setAdminContactNichdl((String) sObject.getField("AdminContactNichdl__c"));
        sfIpJust.setAbuseContact((String) sObject.getField("AbuseContact__c"));
        sfIpJust.setDetailRecordLastModifiedDate(getDate(sObject, "DetailRecordLastModifiedDate__c"));
        sfIpJust.setSubnetGranted((String) sObject.getField("SubnetGranted__c"));


        XmlObject registeredCompanyName__r = sObject.getChild("RegisteredCompanyName__r");
        if (isNotEmpty(registeredCompanyName__r)) {
            sfIpJust.setRegisteredCompanyId((String) registeredCompanyName__r.getField("Id"));
            sfIpJust.setRegisteredCompanyName((String) registeredCompanyName__r.getField("Name"));
        }


        XmlObject partnerName__r = sObject.getChild("PartnerName__r");
        if (isNotEmpty(partnerName__r)) {
            Account account = convertAccount(partnerName__r);
            sfIpJust.setPartner(account);
        }

        XmlObject contact__r = sObject.getChild("Contact__r");
        if (isNotEmpty(contact__r)) {
            Contact contact = convertContact(contact__r);
            sfIpJust.setContact(contact);
        }


        String subnetName = (String) sObject.getField("SubnetName__c");
        if (StringUtils.isNotBlank(subnetName)) {
            sfIpJust.setSubnetName(subnetName);
            sfIpJust.setBriefDescription((String) sObject.getField("BriefDescription__c"));

            for (Iterator<XmlObject> devicesIterator = sObject.getChild("IP_Justification_Details__r").getChildren("records"); devicesIterator.hasNext(); ) {
                XmlObject deviceObj = devicesIterator.next();
                SFIPJustDevice device = convertSfIpJustDevice(deviceObj);
                sfIpJust.getSfipJustDevices().add(device);
            }

            Iterator<XmlObject> diagramIterator = sObject.getChild("Attachments").getChildren("records");
            if (diagramIterator.hasNext()) {
                XmlObject attachmentObj = diagramIterator.next();
                SfAttachment attachment = convertAttachment(attachmentObj);
                sfIpJust.setNetworkDiagram(attachment);
            }
        }

        return sfIpJust;
    }

    private SFIPJustDevice convertSfIpJustDevice(XmlObject sObject) {
        SFIPJustDevice device = prepareObject(new SFIPJustDevice(), sObject);
        device.setEquipmentName((String) sObject.getField("EquipmentName__c"));
        device.setManufacturerName((String) sObject.getField("ManufacturerName__c"));
        device.setModelNumber((String) sObject.getField("ModelNumber__c"));
        device.setOtherData((String) sObject.getField("OtherData__c"));
        device.setDateRequested((String) sObject.getField("DateRequested__c"));
        device.setIpJustId((String) sObject.getField("IP_Justification__c"));
        return device;
    }

    SfAttachment convertAttachment(XmlObject xmlObject) {
        SfAttachment attachment = prepareObject(new SfAttachment(), xmlObject);
        attachment.setBody(Base64.decode(((String) xmlObject.getField("Body")).getBytes()));
        attachment.setParentId((String) xmlObject.getField("ParentId"));
        return attachment;
    }

    SObject convert(FeedItem feedItem) {
        SObject sObject = new SObject();
        sObject.setType("FeedItem");
        sObject.setField("ParentId", feedItem.getParentId());
        sObject.setField("Type", "ContentPost");
        sObject.setField("Body", feedItem.getBody());
        sObject.setField("ContentFileName", feedItem.getFileName());
        sObject.setField("ContentData", feedItem.getContentData());
        return sObject;
    }

    SObject convert(SfIpJust sfIpJust) {
        SObject sObject = new SObject();

        if (sfIpJust.getId() != null) {
            sObject.setId(sfIpJust.getId());
        }

        sObject.setType("IP_Justification__c");

        sObject.setField("ProjectId__c", sfIpJust.getProjectId());
        sObject.setField("PartnerName__c", sfIpJust.getPartner().getId());
        sObject.setField("Contact__c", sfIpJust.getContact().getId());
        sObject.setField("RegisteredCompanyName__c", sfIpJust.getRegisteredCompanyId());
        sObject.setField("Status__c", sfIpJust.getStatus());
        sObject.setField("CompanyAddress__c", sfIpJust.getCompanyAddress());
        sObject.setField("CompanyWebsite__c", sfIpJust.getCompanyWebsite());
        sObject.setField("AdminContactName__c", sfIpJust.getAdminContactName());
        sObject.setField("AdminContactPostalAddress__c", sfIpJust.getAdminContactPostalAddress());
        sObject.setField("AdminContactPhone__c", sfIpJust.getAdminContactPhone());
        sObject.setField("AdminContactEmailAddress__c", sfIpJust.getAdminContactEmailAddress());
        sObject.setField("AdminContactNichdl__c", sfIpJust.getAdminContactNichdl());
        sObject.setField("AbuseContact__c", sfIpJust.getAbuseContact());
        sObject.setField("DetailRecordLastModifiedDate__c", sfIpJust.getDetailRecordLastModifiedDate());
        sObject.setField("SubnetGranted__c", sfIpJust.getSubnetGranted());

        if (sfIpJust.getSubnetName() != null) {
            sObject.setField("SubnetName__c", sfIpJust.getSubnetName());
            sObject.setField("BriefDescription__c", sfIpJust.getBriefDescription());
        }

        return sObject;
    }


    private SObject convert(SFIPJustDevice sfipJustDevice) {
        SObject sObject = new SObject();

        if (sfipJustDevice.getId() != null) {
            sObject.setId(sfipJustDevice.getId());
        }

        sObject.setType("IP_Justification_Detail__c");
        sObject.setField("EquipmentName__c", sfipJustDevice.getEquipmentName());
        sObject.setField("ManufacturerName__c", sfipJustDevice.getManufacturerName());
        sObject.setField("ModelNumber__c", sfipJustDevice.getModelNumber());
        sObject.setField("OtherData__c", sfipJustDevice.getOtherData());
        sObject.setField("DateRequested__c", sfipJustDevice.getDateRequested());
        if (sfipJustDevice.getId() == null) {
            sObject.setField("IP_Justification__c", sfipJustDevice.getIpJustId());
        }
        return sObject;
    }

    SObject[] convertIpJustDevices(List<SFIPJustDevice> devices) {
        SObject[] bulk = new SObject[devices.size()];
        for (int i = 0; i < devices.size(); i++) {
            SObject object = convert(devices.get(i));
            bulk[i] = object;
        }
        return bulk;
    }

    SObject convert(SfAttachment attachment) {
        SObject sObject = new SObject();

        sObject.setId(attachment.getId());
        sObject.setType("Attachment");
        sObject.setField("ParentId", attachment.getParentId());
        sObject.setField("Name", attachment.getName());
        sObject.setField("Body", attachment.getBody());
        return sObject;
    }

    SObject convert(SfEmail sfEmail) {
        SObject sObject = new SObject();
        sObject.setType("EmailMessage");
        sObject.setField("ParentId", sfEmail.getParentId());
        sObject.setField("TextBody", sfEmail.getBody());
        sObject.setField("Subject", sfEmail.getSubject() == null ? "" : sfEmail.getSubject());
        sObject.setField("FromName", sfEmail.getFromName());
        sObject.setField("FromAddress", sfEmail.getFromAddress());
        sObject.setField("Incoming", true);
        sObject.setField("Status", "0");
        sObject.setField("MessageDate", GregorianCalendar.getInstance());
        return sObject;
    }


    SObject convert(Site site) {
        SObject sfObject = new SObject();
        sfObject.setId(site.getId());
        sfObject.setType("Site__c");
        sfObject.setField("Name", site.getName());
        sfObject.setField("Address_c__c", site.getAddress());
        sfObject.setField("Phone__c", site.getPhone());
        sfObject.setField("Post_Code__c", site.getPostCode());
        sfObject.setField("Site_Contact_End__c", site.getSiteContact());
        sfObject.setField("End_Customer_Name__c", site.getEndCustomer().getId());
        sfObject.setField("Unit_Building_number__c", site.getUnitBuildingNumber());
        sfObject.setField("Building_name__c", site.getBuildingName());
        sfObject.setField("Street_number__c", site.getStreetNumber());
        sfObject.setField("Street_name__c", site.getStreetName());
        sfObject.setField("Town_City__c", site.getTownCity());
        sfObject.setField("County__c", site.getCounty());
        sfObject.setField("Address_Ref__c", site.getAddressRef());
        sfObject.setField("Qualifier__c", site.getQualifier());
        sfObject.setField("District_Code__c", site.getDistrictCode());
        sfObject.setField("Building_Constructed_before_year_2000__c", site.getBuildConstructedBefore());
        sfObject.setField("Asbestos_Register_Available_on_Site__c", site.getAsbestos());


        for (Map.Entry<String, Object> entry : site.getCustomFields().entrySet()) {
            sfObject.setField(entry.getKey(), entry.getValue());
        }

        return sfObject;
    }


    // TODO: 02.08.16
    SObject convert(EndCustomer endCustomer) {
        SObject sfObject = new SObject();
        sfObject.setType("End_Customer__c");
        sfObject.setId(endCustomer.getId());
        sfObject.setField("Company_Registration__c", endCustomer.getCompanyRegistration());
        sfObject.setField("Name", endCustomer.getName());
        sfObject.setField("Account_Name__c", endCustomer.getAccountId());
        sfObject.setField("PRTG_Username__c", endCustomer.getPrtgLogin());
        sfObject.setField("PRTG_Password__c", endCustomer.getPrtgPassword());
        return sfObject;
    }


    EndCustomer convertEndCustomer(XmlObject sObject) {
        EndCustomer endCustomer = prepareObject(new EndCustomer(), sObject);
        endCustomer.setCompanyRegistration((String) sObject.getField("Company_Registration__c"));
        endCustomer.setAccountId((String) sObject.getField("Account_Name__c"));
        endCustomer.setPrtgLogin((String) sObject.getField("PRTG_Username__c"));
        endCustomer.setPrtgPassword((String) sObject.getField("PRTG_Password__c"));
        return endCustomer;
    }

    Exchange convertExchange(XmlObject sObject) {
        return prepareObject(new Exchange(), sObject);
    }

    protected SObject convert(Exchange exchange) {
        SObject sfObject = new SObject();
        sfObject.setType("Exchange__c");
        sfObject.setField("Name", exchange.getName());
        sfObject.setField("Exchange_Name__c", exchange.getExchangeName());

        for (Map.Entry<String, Object> entry : exchange.getCustomFields().entrySet()) {
            sfObject.setField(entry.getKey(), entry.getValue());
        }
        return sfObject;
    }

    Site convertSite(XmlObject sObject) {
        Site site = prepareObject(new Site(), sObject);

        site.setPhone((String) sObject.getField("Phone__c"));
        site.setAddress((String) sObject.getField("Address_c__c"));
        site.setPostCode((String) sObject.getField("Post_Code__c"));
        site.setSiteContact((String) sObject.getField("Site_Contact_End__c"));

        site.setUnitBuildingNumber((String) sObject.getField("Unit_Building_number__c"));
        site.setBuildingName((String) sObject.getField("Building_name__c"));
        site.setStreetNumber((String) sObject.getField("Street_number__c"));
        site.setStreetName((String) sObject.getField("Street_name__c"));
        site.setTownCity((String) sObject.getField("Town_City__c"));
        site.setCounty((String) sObject.getField("County__c"));

        site.setAddressRef((String) sObject.getField("Address_Ref__c"));
        site.setQualifier((String) sObject.getField("Qualifier__c"));
        site.setDistrictCode((String) sObject.getField("District_Code__c"));

        site.setBuildConstructedBefore((String) sObject.getField("Building_Constructed_before_year_2000__c"));
        site.setAsbestos((String) sObject.getField("Asbestos_Register_Available_on_Site__c"));

        XmlObject end_customer_name__r = sObject.getChild("End_Customer_Name__r");
        if (isNotEmpty(end_customer_name__r)) {
            EndCustomer endCustomer = convertEndCustomer(end_customer_name__r);
            site.setEndCustomer(endCustomer);
        }
        return site;
    }

    Project convertProjectForCloudRequest(SObject sObject) {
        Project project = new Project();
        project.setId((String) sObject.getField("Id"));
        project.setCustomerSignedBy((String) sObject.getField("Customer_Signed_By__c"));
        project.setProjectStatusDel(getSubField(sObject, "Project_Status_del__c"));
        return project;
    }

    NNI convertNNI(SObject sObject) {
        NNI nni = new NNI();
        nni.setId(sObject.getId());
        nni.setName((String) sObject.getField("Name"));
        nni.setStatus((String) sObject.getField("Status__c"));
        nni.setCarrierNniId((String) sObject.getField("Carrier_NNI_ID__c"));
        nni.setCarrierProvider(getSubField(sObject, "Carrier_Provider__r.Name"));
        nni.setCarrierProviderId(getSubField(sObject, "Carrier_Provider__r.Id"));
        nni.setNniType((String) sObject.getField("NNI_Type__c"));
        nni.setNniDescription((String) sObject.getField("NNI_Description__c"));
        nni.setUpstreamDeviceName((String) sObject.getField("Upstream_Device_Name__c"));
        nni.setRecordType(getSubField(sObject, "RecordType.Name"));
        nni.setRecordTypeId(getSubField(sObject, "RecordType.Id"));
        return nni;
    }

    private Integer convertToInteger(Object source) {
        if (source == null)
            return null;

        if (source instanceof Number)
            return ((Number) source).intValue();

        return (new BigDecimal(source.toString())).intValue();
    }

    Case convertCase(XmlObject sObject) {
        Case sfCase = new Case();

        sfCase.setId((String) sObject.getField("Id"));
        sfCase.setAccountId((String) sObject.getField("AccountId"));
        sfCase.setProjectId((String) sObject.getField("Project_Number1__c"));
        XmlObject accountObj = sObject.getChild("Account");
        if (isNotEmpty(accountObj)) {
            sfCase.setAccountName((String) accountObj.getField("Name"));
        }

        XmlObject contactObj = sObject.getChild("Contact");
        if (isNotEmpty(contactObj)) {
            Contact sfContact = convertContact(contactObj);
            sfCase.setContact(sfContact);
        }

        sfCase.setType((String) sObject.getField("Type"));
        sfCase.setEndCustomerName((String) sObject.getField("End_Customer_Name__c"));

        if ("Provisioning".equals(sfCase.getType())) {
            sfCase.setEscalated(BooleanUtils.toBoolean((String) sObject.getField("Provisioning_Escalated__c")));
        } else {
            sfCase.setEscalated(BooleanUtils.toBoolean((String) sObject.getField("IsEscalated")));
        }
        sfCase.setPriority((String) sObject.getField("Priority"));
        sfCase.setClosed(BooleanUtils.toBoolean((String) sObject.getField("IsClosed")));
        sfCase.setOrigin((String) sObject.getField("Origin"));
        sfCase.setCaseNumber((String) sObject.getField("CaseNumber"));
        sfCase.setStatus((String) sObject.getField("Status"));
        sfCase.setNetOpsOwner(getSubField(sObject, "NetOps_Owner__r.Name"));
        sfCase.setECCsAcceptanceDeadline(getDate(sObject, "ECCs_Acceptance_Deadline__c"));

        sfCase.setReference((String) sObject.getField("Partner_Internal_Ref__c"));

        sfCase.setProjectMgmtAllocated(BooleanUtils.toBoolean((String) sObject.getField("Project_Mngt_Allocated__c")));
        sfCase.setProjectManager(getSubField(sObject, "Project_Number1__r.Project_Manager__r.Name"));

        sfCase.setProvisionalRFS(getDate(sObject, "Provisional_RFS__c"));
        sfCase.setOrderAccepted(getDate(sObject, "Order_Accepted__c"));
        sfCase.setOrderReceived(getDate(sObject, "Order_Received_Date__c"));
        sfCase.setDeliverySchedule((String) sObject.getField("Delivery_Schedule__c"));
        sfCase.setSiteName((String) sObject.getField("Site_Name_B_End__c"));
        sfCase.setPostCode((String) sObject.getField("Post_Code_B_End__c"));
        sfCase.setAddress((String) sObject.getField("Address_B_End__c"));

        sfCase.setSiteSurveyDate(getDate(sObject, "Site_Survey_Date__c"));
        sfCase.setSiteSurveyTime((String) sObject.getField("Site_Survey_Time__c"));
        sfCase.setSiteSurveyDateAEnd(getDate(sObject, "Site_Survey_Date_A_End__c"));
        sfCase.setSiteSurveyTimeAEnd((String) sObject.getField("Site_Survey_Time_A_End__c"));
        sfCase.setSiteSurveyResult((String) sObject.getField("Site_Survey_Results__c"));

        sfCase.setInstallationDate(getDate(sObject, "Installation_Date__c"));
        sfCase.setInstallationTime((String) sObject.getField("Installation_Time__c"));
        sfCase.setInstallationDateAEnd(getDate(sObject, "Installation_Date_A_End__c"));
        sfCase.setInstallationTimeAEnd((String) sObject.getField("Installation_Time_A_End__c"));

        sfCase.setEndToEndTestDate(getDate(sObject, "End_to_End_Test_Date__c"));
        sfCase.setEndToEndTestTime((String) sObject.getField("End_to_End_Test_Time__c"));
        sfCase.setEndToEndTestDateAEnd(getDate(sObject, "End_to_End_Test_Date_A_End__c"));
        sfCase.setEndToEndTestTimeAEnd((String) sObject.getField("End_to_End_Test_Time_A_End__c"));

        sfCase.setDeskTopSurveyCompleted(BooleanUtils.toBoolean((String) sObject.getField("Desk_Top_Survey_Completed__c")));
        sfCase.setDeskTopSurveyDate(getDate(sObject, "Desk_Top_Survey_Date__c"));

        sfCase.setVirtual1EngineerInstallRqd((String) sObject.getField("Virtual1_Engineer_Install_Rqd__c"));
        sfCase.setQubeInstallDate(getDate(sObject, "Qube_Install_Date__c"));
        sfCase.setQubeInstallSlot((String) sObject.getField("Qube_Install_Slot__c"));

        sfCase.setAnalogueCommittedDate(getDate(sObject, "Analogue_Committed_Date__c"));
        sfCase.setAnalogueOrderAcceptedDate(getDate(sObject, "Analogue_Order_Accepted_Date__c"));
        sfCase.setAnalogueInstallationDate(getDate(sObject, "Analogue_Installation_Date__c"));
        sfCase.setAnalogueInstallationTime((String) sObject.getField("Analogue_Installation_Time__c"));

        sfCase.setCarrierProvider((String) sObject.getField("Carrier_Provider__c"));

        sfCase.setCarrierAppointmentDate(getCalendar(sObject, "Carrier_Appointment_Date__c"));
        sfCase.setCarrierAppointmentReference((String) sObject.getField("Carrier_Appointment_Reference__c"));
        sfCase.setCarrierAppointmentTimeslot((String) sObject.getField("Carrier_Appointment_Timeslot__c"));

        sfCase.setMinimumGuaranteedSpeed((String) sObject.getField("MinimumGuaranteedSpeed__c"));

        String leadTime = (String) sObject.getField("Lead_Time_Range__c");
        if (StringUtils.isNotBlank(leadTime)) {
            sfCase.setLeadTime(leadTime);
        }

        sfCase.setCarrierOrderReference((String) sObject.getField("Carrier_Order_Reference__c"));
        sfCase.setInServiceDate(getDate(sObject, "In_Service_Date__c"));

        XmlObject owner = sObject.getChild("Owner");
        if (isNotEmpty(owner)) {
            sfCase.setCaseOwner((String) owner.getField("Name"));
        }

        XmlObject recordType = sObject.getChild("RecordType");
        if (isNotEmpty(recordType)) {
            sfCase.setRecordType((String) recordType.getField("Name"));
        }

        XmlObject accessObj = sObject.getChild("Access_ID__r");
        if (isNotEmpty(accessObj)) {
            sfCase.setAccess(convertAccess(accessObj));
        }

        XmlObject assetAEndObj = sObject.getChild("Asset_ID_A_End__r");
        if (isNotEmpty(assetAEndObj)) {
            sfCase.setAssetAEnd(convertAsset(assetAEndObj));
        }

        XmlObject assetBEndObj = sObject.getChild("Asset_ID_B_End__r");
        if (isNotEmpty(assetBEndObj)) {
            sfCase.setAssetBEnd(convertAsset(assetBEndObj));
        }

        sfCase.setReason((String) sObject.getField("Reason"));
        sfCase.setSubject((String) sObject.getField("Subject"));
        sfCase.setDescription((String) sObject.getField("Description"));
        sfCase.setCreated(getDate(sObject, "CreatedDate"));
        sfCase.setEstimatedCompletionDate(getDate(sObject, "Estimated_Completion_Date__c"));
        sfCase.setNextUpdateDue(getDate(sObject, "Next_Update_Due__c"));
        sfCase.setCarrierOrderReference((String) sObject.getField("Carrier_Order_Reference__c"));


        for (Iterator<XmlObject> iterator = sObject.getChildren("CaseComments"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject caseCommentObj = iterator2.next();
                CaseComment caseComment = convertCaseComment(caseCommentObj);
                sfCase.getCaseComments().add(caseComment);
            }
        }

        for (Iterator<XmlObject> iterator = sObject.getChildren("CaseContactRoles"); iterator != null && iterator.hasNext(); ) {
            XmlObject xmlObject = iterator.next();
            for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                XmlObject caseContactRoleObj = iterator2.next();
                CaseContactRole caseContactRole = convertCaseContactRole(caseContactRoleObj);
                sfCase.getCaseContactRoles().add(caseContactRole);
            }
        }

        return sfCase;
    }

    /**
     * Parses case comments to format the email addresses
     */
    private CaseComment convertCaseComment(XmlObject sObject) {
        CaseComment comment = prepareObject(new CaseComment(), sObject);

        comment.setBody((String) sObject.getField("CommentBody"));
        comment.setCaseId((String) sObject.getField("ParentId"));
        comment.setPublished(getBoolean(sObject, "IsPublished"));

        XmlObject createdBy = sObject.getChild("CreatedBy");
        if (isNotEmpty(createdBy)) {
            comment.setCreatorName((String) createdBy.getField("Name"));
        }

        //parse body  ([origin] Comment From: [email] Comment: [comment])
        if (StringUtils.contains(comment.getBody(), "Comment From:") && StringUtils.contains(comment.getBody(), "Comment:")) {
            int emailIdx = comment.getBody().indexOf("Comment From:") + 13;
            int emailLastIdx = comment.getBody().indexOf("Comment:", emailIdx);

            if (emailIdx < 30 && emailIdx > 0 && emailLastIdx > emailIdx) {
                String email = comment.getBody().substring(emailIdx, emailLastIdx).trim();
                String content = comment.getBody().substring(emailLastIdx + 8).trim();

                comment.setCreatorName(email);
                comment.setBody(content);
            }
        }

        return comment;
    }

    CloudProvisioning convertCloudProvisioning(SObject sObject) {
        CloudProvisioning cloudProvisioning = new CloudProvisioning();
        cloudProvisioning.setMaximumCPU(Double.parseDouble((String) sObject.getField("Demo_Maximum_CPU__c")));
        cloudProvisioning.setMaximumDisk(Double.parseDouble((String) sObject.getField("Demo_Maximum_Disk__c")));
        cloudProvisioning.setMaximumRAM(Double.parseDouble((String) sObject.getField("Demo_Maximum_RAM__c")));
        cloudProvisioning.setMaximumIPAddress(Double.parseDouble((String) sObject.getField("Demo_Maximum_IP_Address__c")));
        return cloudProvisioning;
    }

    CloudProvisioning convertCloudProvisioningFromAssets(List<SObject> sObjects) {
        for (SObject sObject : sObjects) {

            for (Iterator<XmlObject> iterator = sObject.getChildren("Assets__r"); iterator != null && iterator.hasNext(); ) {
                XmlObject xmlObject = iterator.next();

                for (Iterator<XmlObject> iterator2 = xmlObject.getChildren("records"); iterator2 != null && iterator2.hasNext(); ) {
                    XmlObject objAsset = iterator2.next();

                    CloudProvisioning cloudProvisioning = new CloudProvisioning();
                    Object maximumCpuObject = objAsset.getField("Maximum_CPU__c");
                    if (maximumCpuObject != null) {
                        cloudProvisioning.setMaximumCPU(Double.valueOf((String) maximumCpuObject));
                    }
                    Object maximumRamObject = objAsset.getField("Maximum_RAM__c");

                    if (maximumRamObject != null) {
                        cloudProvisioning.setMaximumRAM(Double.valueOf((String) maximumRamObject));
                    }

                    Object maximumDiskObject = objAsset.getField("Maximum_Disk__c");

                    if (maximumDiskObject != null) {
                        cloudProvisioning.setMaximumDisk(Double.valueOf((String) maximumDiskObject));
                    }

                    Object numberIpAddressesObject = objAsset.getField("Number_IP_Addresses__c");

                    if (numberIpAddressesObject != null) {
                        cloudProvisioning.setMaximumIPAddress(Double.valueOf((String) numberIpAddressesObject));
                    }

                    return cloudProvisioning;
                }

            }
        }
        return null;
    }

    private Double toDouble(BigDecimal amount) {
        if (amount == null) {
            return null;
        }
        return amount.doubleValue();
    }

    private String getSubField(XmlObject sObject, String path) {
        String[] paths = path.split("\\.");

        XmlObject child = sObject;
        for (int i = 0; i < paths.length - 1; i++) {
            child = child.getChild(paths[i]);

            if (child == null) {
                return null;
            }
        }

        return (String) child.getField(paths[paths.length - 1]);
    }

    private Date getDate(XmlObject sObject, String fieldName) {
        String strDate = (String) sObject.getField(fieldName);
        return StringUtils.isNotBlank(strDate) ? ISODateTimeFormat.dateTimeParser().parseDateTime(strDate).toDate() : null;
    }

    private boolean getBoolean(XmlObject sObject, String fieldName) {
        String str = (String) sObject.getField(fieldName);
        return Boolean.parseBoolean(str);
    }

    private Calendar getCalendar(XmlObject sObject, String fieldName) {
        Date date = getDate(sObject, fieldName);
        if (date != null) {
            Calendar result = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            result.setTime(date);
            return result;
        } else {
            return null;
        }
    }

    private <T extends BaseSalesforceObject> T prepareObject(T t, XmlObject sObject) {
        t.setId((String) sObject.getField("Id"));
        t.setName((String) sObject.getField("Name"));
        t.setCreatedDate(getDate(sObject, "CreatedDate"));
        t.setLastModifiedDate(getDate(sObject, "LastModifiedDate"));
        return t;
    }

    private boolean isNotEmpty(XmlObject xmlObject) {
        return xmlObject != null && xmlObject.getChildren().hasNext();
    }

}
