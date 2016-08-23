package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceParentId;
import uk.co.virtual1.salesforce.cache.SalesforceRelation;

import java.util.Date;

public class Asset extends AbstractCaseItem {
    private static final long serialVersionUID = 1L;

    @SalesforceRelation
    private Site site;

    private String assetType; //Asset_Type_New__c
    private String assetManaged; // Asset_Managed__c
    private String status; // Status__c
    private String cpeName;// CPE_Name__c
    private Date shippedDate; // Shipped_Date__c
    private Date created;
    private String category; //Asset_Category__c
    @Deprecated
    private String oneViewLink;
    private Date inServiceDate;//In_Service_Date
    private Date contractEndDate; //Service_End_Date__c
    private String configNotes; //Config_Notes__c
    private String x1TaggingMethod; //X1_Tagging_Method__c

    @SalesforceParentId
    private String projectId; //Project_Number__c

    private String x1Autonegotiation; //X1_Autonegotiation__c
    private String x1InterfaceSpeed; //X1_Interface_Speed__c
    private String x1Duplex; //X1_Duplex__c
    private String x1InterfaceType; //X1_Interface_Type__c
    private Integer serviceTerms;//Service_Term_months__c
    private String shippingContact;//Shipping_Contact__c
    private String shippingAddress;//Shipping_Address__c
    private boolean virtual1EngineerInstallRqd; // Yes/No

    private String shipTo;//Ship_to__c
    private String enterpriseName;  //Enterprise_Name__c

    private String rackSize; //Rack_Size
    private String powerRatings; //Power_Ratings
    private String designType; //DesignType__c

    private String instanceKey; //MonitoringInstance__r.Name
    private String oneViewId; //MonitoringID__c


    @Override
    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the assetType
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * @param assetType the assetType to set
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    /**
     * @return the assetManaged
     */
    public String getAssetManaged() {
        return assetManaged;
    }

    /**
     * @param assetManaged the assetManaged to set
     */
    public void setAssetManaged(String assetManaged) {
        this.assetManaged = assetManaged;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the cpeName
     */
    public String getCpeName() {
        return cpeName;
    }

    /**
     * @param cpeName the cpeName to set
     */
    public void setCpeName(String cpeName) {
        this.cpeName = cpeName;
    }

    /**
     * @return the shippedDate
     */
    public Date getShippedDate() {
        return shippedDate;
    }

    /**
     * @param shippedDate the shippedDate to set
     */
    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getInServiceDate() {
        return inServiceDate;
    }

    public void setInServiceDate(Date inServiceDate) {
        this.inServiceDate = inServiceDate;
    }

    @Deprecated
    public String getOneViewLink() {
        return oneViewLink;
    }

    @Deprecated
    public void setOneViewLink(String oneViewLink) {
        this.oneViewLink = oneViewLink;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getServiceTerms() {
        return serviceTerms;
    }

    public void setServiceTerms(Integer serviceTerms) {
        this.serviceTerms = serviceTerms;
    }

    public String getShippingContact() {
        return shippingContact;
    }

    public void setShippingContact(String shippingContact) {
        this.shippingContact = shippingContact;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public boolean isVirtual1EngineerInstallRqd() {
        return virtual1EngineerInstallRqd;
    }

    public void setVirtual1EngineerInstallRqd(boolean virtual1EngineerInstallRqd) {
        this.virtual1EngineerInstallRqd = virtual1EngineerInstallRqd;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getRackSize() {
        return rackSize;
    }

    public void setRackSize(String rackSize) {
        this.rackSize = rackSize;
    }

    public String getPowerRatings() {
        return powerRatings;
    }

    public void setPowerRatings(String powerRatings) {
        this.powerRatings = powerRatings;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getConfigNotes() {
        return configNotes;
    }

    public void setConfigNotes(String configNotes) {
        this.configNotes = configNotes;
    }

    public String getX1TaggingMethod() {
        return x1TaggingMethod;
    }

    public void setX1TaggingMethod(String x1TaggingMethod) {
        this.x1TaggingMethod = x1TaggingMethod;
    }

    public String getX1Autonegotiation() {
        return x1Autonegotiation;
    }

    public void setX1Autonegotiation(String x1Autonegotiation) {
        this.x1Autonegotiation = x1Autonegotiation;
    }

    public String getX1InterfaceSpeed() {
        return x1InterfaceSpeed;
    }

    public void setX1InterfaceSpeed(String x1InterfaceSpeed) {
        this.x1InterfaceSpeed = x1InterfaceSpeed;
    }

    public String getX1Duplex() {
        return x1Duplex;
    }

    public void setX1Duplex(String x1Duplex) {
        this.x1Duplex = x1Duplex;
    }

    public String getX1InterfaceType() {
        return x1InterfaceType;
    }

    public void setX1InterfaceType(String x1InterfaceType) {
        this.x1InterfaceType = x1InterfaceType;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getDesignType() {
        return designType;
    }

    public void setDesignType(String designType) {
        this.designType = designType;
    }

    public String getInstanceKey() {
        return instanceKey;
    }

    public void setInstanceKey(String instanceKey) {
        this.instanceKey = instanceKey;
    }

    public String getOneViewId() {
        return oneViewId;
    }

    public void setOneViewId(String oneViewId) {
        this.oneViewId = oneViewId;
    }
}
