package uk.co.virtual1.salesforce;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import static java.lang.String.format;

/**
 * @author Mikhail Tkachenko created on 28.07.16 14:22
 */
class QueryTemplates {

    final static String COMMON_FIELDS = "Id,Name,CreatedDate,LastModifiedDate";


    final static String CONTACT_FIELDS = COMMON_FIELDS + ",AccountId,FirstName,LastName,Email,Phone,Title,Role__c";


    final static String ATTACHMENT_FIELDS = COMMON_FIELDS + ",Body,ParentId";


    final static String ANALOGUE_LINE_FIELDS = COMMON_FIELDS + ",Call_Barring__c,Status__c";


    final static String END_CUSTOMER_FIELDS = COMMON_FIELDS + ",Account_Name__c,Company_Registration__c,PRTG_Username__c,PRTG_Password__c";


    final static String RECORD_TYPE_FIELDS = COMMON_FIELDS + ",SobjectType,Description";


    final static String SITE_FIELDS = COMMON_FIELDS + "," +
            "Address_c__c,Phone__c,Post_Code__c,Site_Contact_End__c," +
            "Unit_Building_number__c,Building_Name__c,Street_number__c,Street_name__c," +
            "Town_City__c, County__c,Address_Ref__c, District_Code__c,Qualifier__c," +
            "Building_Constructed_before_year_2000__c,Asbestos_Register_Available_on_Site__c," +
            relation("End_Customer_Name__r", END_CUSTOMER_FIELDS);


    final static String ACCOUNT_FIELDS = COMMON_FIELDS + "," +
            "RecordType.Name, " +
            "PRTG_Username__c,PRTG_Password__c, " +
            "Owner.Id,Owner.Name, " +
            "Owner.Email,Owner.MobilePhone,PO_Number_Required__c, " +
            "BillingStreet, BillingCity, BillingState, BillingPostalCode, " +
            "BillingCountry, " +
            "ShippingStreet, ShippingCity, ShippingState, ShippingPostalCode, " +
            "ShippingCountry, Registered_Name__c,Co_Reg__c, COE_Approved__c, RID_Number__c, X1Cloud_Demo_Request_Blackout__c, " +
            "Status__c,PBT__c";

    private final static String VLAN_FIELDS = COMMON_FIELDS + "," +
            "CIR__c, LAN_IPv4_Gateway__c, VPN__r.Name,VPN__r.VPN_Type__c, Related_LAN_Port__c, RecordType.Name, NNI__r.Carrier_NNI_ID__c";


    private final static String INTERNATIONAL_VLAN_FIELDS = COMMON_FIELDS + "," +
            "CIR_Mb__c,LAN_IPv4_Gateway__c,Intl_VPN__r.Name,Intl_VPN__r.VPN_Type__c,Related_LAN_Port__c";


    private final static String RADIUS_FIELDS = COMMON_FIELDS + ",LAN_IPv4_Gateway__c,VPN__r.Name,VPN__r.VPN_Type__c, Username__c,Password__c";


    private final static String PRICING_ENTRY_FIELDS = COMMON_FIELDS + ",Charge_Type__r.Name,Billing_Frequency__c,Amount__c,Billing_Start_Date__c,Billing_End_Date__c";


    private final static String ACCESS_FIELDS = COMMON_FIELDS + "," +
            "Account_Name__c,End_Customer_Name__c, " +
            "Access_Type__c,Service_Type__c,Carrier_Provider__c,Carrier_Provider__r.Name, Carrier_Service_ID__c, Status__c, " +
            "ADSL_Telephone__c,Bandwidth_Sold__c,Bearer_Speed__c,In_Service_Date__c,Service_End_Date__c, Relation__c, " +
            "Carrier_Interface_B_End__c,Carrier_Contract_months__c, " +
            "Carrier_Product_Name_new__c, " +
            "Floor_B_End__c, Room_B_End__c, Rack_B_End__c, " +
            relation("Site_Name_A_End__r", SITE_FIELDS) + ", " +
            relation("Site_Name_B_End__r", SITE_FIELDS);


    final static String ACCESS_FIELDS_WITH_COLLECTIONS = ACCESS_FIELDS + ", " +
            collection("VLANS__r", VLAN_FIELDS) + ", " +
            collection("International_VLANS__r", INTERNATIONAL_VLAN_FIELDS) + ", " +
            collection("Radius__r", RADIUS_FIELDS) + ", " +
            collection("Pricing_Entries__r", PRICING_ENTRY_FIELDS);


    final static String ASSET_FIELDS = COMMON_FIELDS + "," +
            "RecordType.Name, " +
            "CPE_Name__c, Shipped_Date__c, In_Service_Date__c, Service_End_Date__c, x1viewlink__c," +
            "Asset_Type_New__c, Asset_Managed__c,Status__c, " +
            "Asset_Category__c, Enterprise_Name__c, Rack_Size__c, Power_Ratings__c, " +
            "MonitoringInstance__r.Name,MonitoringID__c, " +
            relation("Site_Name__r", SITE_FIELDS);

    final static String ASSET_FIELDS_WITH_COLLECTIONS = ASSET_FIELDS + ", " +
            collection("Pricing_Entries__r", PRICING_ENTRY_FIELDS);


    final static String INNER_VLAN_FIELDS = COMMON_FIELDS + "," +
            "CIR_Mb__c,VPN__r.Name,VPN__r.VPN_Type__c, LAN_IPv4_Gateway__c,  Related_LAN_Port__c, Access_ID__c, " +
            "Outer_VLAN_Number__r.Name";


    final static String CLOUD_PROVISIONING_FIELDS = "Demo_Maximum_CPU__c,Demo_Maximum_RAM__c, Demo_Maximum_Disk__c, Demo_Maximum_IP_Address__c";


    final static String CASE_COMMENT_FIELDS = "Id,CreatedDate,LastModifiedDate,ParentId,CommentBody,IsPublished,CreatedBy.Name";

    private final static String CASE_CONTACT_ROLE_FIELDS = "Id,CreatedDate,LastModifiedDate,CasesId,Role,IsDeleted, " +
            relation("Contact", CONTACT_FIELDS);

    @Deprecated
    final static String CASE_FIELDS_SHORT = " Id,CaseNumber,Type,CreatedDate,Reason,Status";

    private final static String CASE_FIELDS = "Id,CreatedDate,LastModifiedDate,Owner.Name,AccountId,Account.Name," +
            "ContactId,Contact.Id,Contact.AccountId,Contact.Name,Contact.FirstName,Contact.LastName,Contact.Phone,Contact.Email,Contact.Title,Contact.Department,Contact.Role__c," +
            "RecordType.Name,CaseNumber,Type,Reason," +
            " Subject, Description,IsEscalated, Provisioning_Escalated__c,Priority,IsClosed,Origin," +
            " Order_Accepted__c,Lead_Time_Range__c, Carrier_Order_Reference__c, " +
            " Status,End_Customer_Name__c,Provisional_RFS__c,ECCs_Acceptance_Deadline__c, Partner_Internal_Ref__c," +
            " NetOps_Owner__r.Name, Order_Received_Date__c, Project_Mngt_Allocated__c, Project_Number1__r.Project_Manager__r.Name," +
            " Delivery_Schedule__c, Site_Survey_Date__c, Site_Survey_Time__c, " +
            " Site_Name_B_End__c, Post_Code_B_End__c, Address_B_End__c, " +
            " Site_Survey_Results__c, Installation_Date__c, Installation_Date_A_End__c, " +
            " Site_Survey_Date_A_End__c, Site_Survey_Time_A_End__c, Installation_Time_A_End__c, " +
            " End_to_End_Test_Date__c, " +
            " In_Service_Date__c, " +
            " End_to_End_Test_Date_A_End__c, End_to_End_Test_Time_A_End__c, " +
            " Desk_Top_Survey_Completed__c, Desk_Top_Survey_Date__c," +
            " Installation_Time__c, End_to_End_Test_Time__c, Virtual1_Engineer_Install_Rqd__c, " +
            " Qube_Install_Date__c, Qube_Install_Slot__c," +
            " Analogue_Order_Accepted_Date__c, Analogue_Committed_Date__c,Analogue_Installation_Date__c,Analogue_Installation_Time__c,Carrier_Provider__c," +
            " Estimated_Completion_Date__c, MinimumGuaranteedSpeed__c," +
            " Next_Update_Due__c, Carrier_Appointment_Date__c, Carrier_Appointment_Reference__c, Carrier_Appointment_Timeslot__c, Project_Number1__c," +
            relation("Asset_ID_A_End__r", ASSET_FIELDS) + ", " +
            relation("Asset_ID_B_End__r", ASSET_FIELDS) + ", " +
            relation("Access_ID__r", ACCESS_FIELDS);

    final static String CASE_FIELDS_WITH_COLLECTIONS = CASE_FIELDS + ", " +
            collection("CaseComments", CASE_COMMENT_FIELDS) + ", " +
            collection("CaseContactRoles", CASE_CONTACT_ROLE_FIELDS);


    private final static String IP_JUST_DEVICE_FIELDS = COMMON_FIELDS + ",EquipmentName__c,ManufacturerName__c,ModelNumber__c,OtherData__c,DateRequested__c,IP_Justification__c";

    private final static String IP_JUST_FIELDS = COMMON_FIELDS + ",ProjectId__c,ProjectId__r.Project_Number__c," +
            "Status__c,SubnetGranted__c,SubnetName__c, BriefDescription__c, " +
            "RegisteredCompanyName__r.Id, RegisteredCompanyName__r.Name,CompanyAddress__c,CompanyWebsite__c, " +
            "AdminContactName__c,AdminContactPostalAddress__c,AdminContactPhone__c,AdminContactEmailAddress__c," +
            "AdminContactNichdl__c,AbuseContact__c,DetailRecordLastModifiedDate__c, " +
            relation("PartnerName__r", ACCOUNT_FIELDS) + ", " +
            relation("Contact__r", CONTACT_FIELDS);

    final static String IP_JUST_FIELDS_WITH_COLLECTIONS = IP_JUST_FIELDS + ", " +
            "(SELECT Id,Name,CreatedDate,LastModifiedDate FROM Attachments ORDER BY LastModifiedDate DESC LIMIT 1), " +
            collection("IP_Justification_Details__r", IP_JUST_DEVICE_FIELDS);

    final static String PROJECT_FIELDS = COMMON_FIELDS + "," +
            "Special_Instructions__c,Project_Mgmt_Allocated__c,Project_Type__c,Project_Status_del__c,Project_Number__c," +
            "NetOps_Owner__r.Name, Owner.Name,Project_Manager__r.Name," +
            relation("Technical_Contact_Name__r", CONTACT_FIELDS) + ", " +
            relation("Account_Name__r", ACCOUNT_FIELDS) + ", " +
            relation("End_Customer_Name__r", END_CUSTOMER_FIELDS);

    final static String PROJECT_FIELDS_WITH_COLLECTIONS = PROJECT_FIELDS + ", " +
            "(SELECT " + COMMON_FIELDS + ",RecordType.Name,Site_Name__r.Name,End_Customer_Name__c,Site_Name__r.End_Customer_Name__r.Id,Asset_Type_New__c," +
            "Status__c,Asset_Category__c FROM Assets__r), " +

            "(SELECT " + COMMON_FIELDS + ",RecordType.Name,Site_Name_B_End__c,Site_Name_B_End__r.Name,Relation__c,End_Customer_Name__c,Site_Name_B_End__r.End_Customer_Name__r.Id," +
            "Access_Type__c,Service_Type__c,Carrier_Provider__c,Carrier_Provider__r.Name,Status__c,Asset_ID_B_End__r.Name FROM Access__r), " +

            "(SELECT Id,CreatedDate,LastModifiedDate,CaseNumber,Reason,Status,Provisional_RFS__c," +
            "Access_ID__r.Id,Access_ID__r.Name,Access_ID__r.Access_Type__c,Access_ID__r.Site_Name_B_End__r.Name," +
            "Asset_ID_B_End__r.Id, Asset_ID_B_End__r.Name,Asset_ID_B_End__r.Status__c,Asset_ID_B_End__r.Asset_Type_New__c," +
            "Carrier_Provider__c FROM Cases__r WHERE Hidden_From_1Portal__c = false), " +

            "(SELECT " + COMMON_FIELDS + ",Site_Name__c,Component_Category__c,Component_Type_New__c,Status__c,Asset_ID__r.Name FROM Components__r)";


    final static String VPN_FIELDS = COMMON_FIELDS + ",RecordType.Name,VPN_Type__c,Network_Diagram__c," +
            relation("End_Customer__r", END_CUSTOMER_FIELDS) + ", " +

            "(SELECT Id,Name,CIR__c,Account_Name__c,End_Customer_Name__c," +
            "Access_ID__r.Name," +
            "Access_ID__r.Access_Type__c," +
            "Access_ID__r.Relation__c," +
            "Access_ID__r.Status__c," +
            "Access_ID__r.Site_Name_B_End__r.Name," +
            "Access_ID__r.Site_Name_B_End__r.End_Customer_Name__r.Id FROM VLANS__r), " +

            "(SELECT Id,Name,CIR_Mb__c,Access_ID__c,Account_Name__c,End_Customer_Name__c," +
            "Outer_VLAN_Number__r.Access_ID__r.Name," +
            "Outer_VLAN_Number__r.Access_ID__r.Relation__c," +
            "Outer_VLAN_Number__r.Access_ID__r.Status__c," +
            "Outer_VLAN_Number__r.Access_ID__r.Access_Type__c," +
            "Outer_VLAN_Number__r.Access_ID__r.Site_Name_B_End__r.Name," +
            "Outer_VLAN_Number__r.Access_ID__r.Site_Name_B_End__r.End_Customer_Name__r.Id FROM Inner_VLANs__r), " +

            "(SELECT Id,Name,Access_ID__r.Name," +
            "Access_ID__r.Access_Type__c," +
            "Access_ID__r.Relation__c, " +
            "Access_ID__r.Status__c, " +
            "Access_ID__r.Site_Name_B_End__r.Name," +
            "Account_Name__c,End_Customer_Name__c," +
            "Access_ID__r.Site_Name_B_End__r.End_Customer_Name__r.Id FROM Radius__r)";


    final static String NNI_FIELDS = COMMON_FIELDS + ",Status__c,Carrier_NNI_ID__c,Carrier_Provider__r.Name,Carrier_Provider__r.Id," +
            "NNI_Type__c,NNI_Description__c, Upstream_Device_Name__c, RecordType.Id, RecordType.Name";


    private static String relation(String relationName, String fields) {
        StringBuilder result = new StringBuilder();
        String[] split = fields.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            result.append(relationName).append(".").append(s.trim());
            if (i != split.length - 1) {
                result.append(", ");
            }

        }
        return result.toString();
    }

    private static String collection(String collectionName, String fields) {
        return format("(SELECT %s FROM %s)", fields, collectionName);
    }

    private static String collection(String collectionName, String fields, String condition) {
        return format("(SELECT %s FROM %s WHERE %s)", fields, collectionName, condition);
    }

    static String in(Collection<? extends Serializable> list) {
        StringBuilder builder = new StringBuilder("(");
        Iterator<? extends Serializable> iterator = list.iterator();
        while (iterator.hasNext()) {
            builder.append("'").append(String.valueOf(iterator.next())).append("'");
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }
        return builder.append(")").toString();
    }

    /**
     * http://www.salesforce.com/us/developer/docs/api/Content/sforce_api_calls_soql_select_quotedstringescapes.htm
     */
    static String escapeSOQL(String searchQuery) {
        String result = searchQuery;
        String[] reservedChars = {"\\", "'", "\""};

        for (String res : reservedChars) {
            result = result.replace(res, "\\" + res);
        }
        return result;
    }

}
