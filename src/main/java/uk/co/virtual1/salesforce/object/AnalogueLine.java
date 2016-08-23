package uk.co.virtual1.salesforce.object;


import java.util.Date;

public class AnalogueLine extends BaseSalesforceObject {

    private String accessID; //Access_ID__c
    private String annualRentalCost; //Annual_Rental_Cost__c (blank for now)
    private Integer carrierContractMths; //Carrier_Contract_mths__c
    private Date carrierEndDate; //Carrier_End_Date__c (derived field on salesforce)
    private Date carrierLiveDate; //Carrier_Live_Date__c (blank for now)
    private String carrierProductName; //Carrier_Product_Name__c
    private String carrierProvider; //Carrier_Provider__c
    private String carrierServiceID; //Carrier_Service_ID__c (blank for now)
    private String endCustomerName; //End_Customer_Name__c (derived field on salesforce)
    private String oneOffCost; //One_Off_Cost__c (blank for now)
    private String projectNumber; //Project_Number__c
    private String projectStatus; //Project_Status__c (derived field on salesforce)
    private String siteName; //Site_Name__c (derived field on salesforce)
    private String status; //Status__c
    private boolean callBarring;    // Call_Barring__c

    public String getAccessID() {
        return accessID;
    }

    public void setAccessID(String accessID) {
        this.accessID = accessID;
    }

    public String getAnnualRentalCost() {
        return annualRentalCost;
    }

    public void setAnnualRentalCost(String annualRentalCost) {
        this.annualRentalCost = annualRentalCost;
    }

    public Integer getCarrierContractMths() {
        return carrierContractMths;
    }

    public void setCarrierContractMths(Integer carrierContractMths) {
        if (carrierContractMths > 0) {
            this.carrierContractMths = carrierContractMths;
        }
    }

    public Date getCarrierEndDate() {
        return carrierEndDate;
    }

    public void setCarrierEndDate(Date carrierEndDate) {
        this.carrierEndDate = carrierEndDate;
    }

    public Date getCarrierLiveDate() {
        return carrierLiveDate;
    }

    public void setCarrierLiveDate(Date carrierLiveDate) {
        this.carrierLiveDate = carrierLiveDate;
    }

    public String getCarrierProductName() {
        return carrierProductName;
    }

    public void setCarrierProductName(String carrierProductName) {
        this.carrierProductName = carrierProductName;
    }

    public String getCarrierProvider() {
        return carrierProvider;
    }

    public void setCarrierProvider(String carrierProvider) {
        this.carrierProvider = carrierProvider;
    }

    public String getCarrierServiceID() {
        return carrierServiceID;
    }

    public void setCarrierServiceID(String carrierServiceID) {
        this.carrierServiceID = carrierServiceID;
    }

    public String getEndCustomerName() {
        return endCustomerName;
    }

    public void setEndCustomerName(String endCustomerName) {
        this.endCustomerName = endCustomerName;
    }

    public String getOneOffCost() {
        return oneOffCost;
    }

    public void setOneOffCost(String oneOffCost) {
        this.oneOffCost = oneOffCost;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCallBarring() {
        return callBarring;
    }

    public void setCallBarring(boolean callBarring) {
        this.callBarring = callBarring;
    }

}

