package uk.co.virtual1.salesforce;

/**
 * @author Mikhail Tkachenko created on 26.07.16 12:57
 */
public interface SalesforceConstants {

    // project
    String PROJECT_TYPE_INTERNET_ACCESS = "Internet Access";
    String PROJECT_TYPE_MPLS = "MPLS";
    String PROJECT_TYPE_LAYER_2 = "Layer 2";

    // asset
    String ASSET_TYPE_1CLOUD_ENTERPRISE = "1Cloud Enterprise";

    // сase
    String CASE_CONTACT_ROLE_TECHNICAL = "Technical Contact";
    String CASE_CONTACT_ROLE_BUSINESS = "Business Contact";
    String CASE_CONTACT_ROLE_DECISION = "Decision Maker";
    String CASE_CONTACT_ROLE_OTHER = "Other";

    // pricing entry
    String ACCESS_PRICING_ENTRY_TYPE = "Access Pricing Entry";
    String ASSET_PRICING_ENTRY_TYPE = "Asset Pricing Entry";
    String COMPONENT_PRICING_ENTRY_TYPE = "Component Pricing Entry";
    String BILLING_FREQUENCY_QUARTERLY = "Quarterly";

    //record types
    String ACCESS_RECORD_TYPE = "Access__c";
    String ASSET_RECORD_TYPE = "Assets__c";
    String COMPONENT_RECORD_TYPE = "Component__c";
    String CASE_RECORD_TYPE = "Case";
    String PRICING_ENTRY_RECORD_TYPE = "Pricing_Entry__c";


    // uncatalogued
    String ROLE_PROVISIONING = "Provisioning";

    String POINT_TO_POINT_RECORD_TYPE = "Ethernet Point-to-Point";

}