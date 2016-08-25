package uk.co.virtual1.salesforce.object;

import java.util.Date;

public class NetOpsCase extends BaseSalesforceObject {

    private static final long serialVersionUID = 1L;

    private String accessName;//Access_ID_Name__c

    private String serviceType;

    private String accessType;//Access_Type__c

    private String endCustomerName; // End_Customer_Name__c

    private String siteName; // Site_Name_B_End__c

    private String netOpsOwnerName;//NetOps_Owner__c

    private Date rfsDate;//Provisional_RFS__c

    private Date circuitOrderedDate;//Ordered_with_Supplier_Date__c

    private Date surveyDate;//Site_Survey_Date__c

    private String surveyResult; // Site_Survey_Results__c

    private Date installationDate; //Installation_Date__c

    private String assetName;//Case.Asset_ID_B_End__c.Name

    private boolean routerOrdered; //Case.Asset_ID_B_End__c.PO_Number_del__c

    private boolean loopBackAssigned;  //Case.Asset_ID_B_End__c.Loop_Back_Address__c

    private boolean routerShipped; //Case.Asset_ID_B_End__c.Status__c

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getEndCustomerName() {
        return endCustomerName;
    }

    public void setEndCustomerName(String endCustomerName) {
        this.endCustomerName = endCustomerName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getNetOpsOwnerName() {
        return netOpsOwnerName;
    }

    public void setNetOpsOwnerName(String netOpsOwnerName) {
        this.netOpsOwnerName = netOpsOwnerName;
    }

    public Date getRfsDate() {
        return rfsDate;
    }

    public void setRfsDate(Date rfsDate) {
        this.rfsDate = rfsDate;
    }

    public Date getCircuitOrderedDate() {
        return circuitOrderedDate;
    }

    public void setCircuitOrderedDate(Date circuitOrderedDate) {
        this.circuitOrderedDate = circuitOrderedDate;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public String getSurveyResult() {
        return surveyResult;
    }

    public void setSurveyResult(String surveyResult) {
        this.surveyResult = surveyResult;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public boolean isRouterOrdered() {
        return routerOrdered;
    }

    public void setRouterOrdered(boolean routerOrdered) {
        this.routerOrdered = routerOrdered;
    }

    public boolean isLoopBackAssigned() {
        return loopBackAssigned;
    }

    public void setLoopBackAssigned(boolean loopBackAssigned) {
        this.loopBackAssigned = loopBackAssigned;
    }

    public boolean isRouterShipped() {
        return routerShipped;
    }

    public void setRouterShipped(boolean routerShipped) {
        this.routerShipped = routerShipped;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }


}
