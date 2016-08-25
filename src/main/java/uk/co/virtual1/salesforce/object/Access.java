package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceCollection;
import uk.co.virtual1.salesforce.cache.SalesforceParentId;
import uk.co.virtual1.salesforce.cache.SalesforceRelation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Access extends AbstractCaseItem {
    private static final long serialVersionUID = 1L;

    @SalesforceRelation
    private Site siteAEnd; // Site_Name_A_End__c
    @SalesforceRelation
    private Site siteBEnd; // Site_Name_B_End__c
    @SalesforceRelation
    private Exchange exchange; //Serving_Exchange_Code__c

    @SalesforceParentId
    private String projectId;

    private String accessType;//Access_Type__c
    private String serviceType;//Service_Type__c
    private String status;//Status__c

    private String providerId;//Carrier_Provider__c
    private String provider;//Carrier_Provider__r.Name
    private String providerProductName;//Carrier_Product_Name_new__c
    private Integer carrierContract;//Carrier_Contract_months__c
    private String serviceId;//Carrier_Service_ID__c

    private String endCustomerName;

    private String relation; //Relation__c

    private String assetBEndName;
    private String floorBEnd; //Floor_B_End__c
    private String roomBEnd;//Room_B_End__c
    private String rackBEnd; //Rack_B_End__c
    private String interfaceType; // Interface_Type_B_End__c
    private String assetBEndId; // Asset_ID_B_End__c

    //access details
    private String telephone;
    private String bandwidthSold; // Bandwidth_Sold__c
    private String circuitID;
    private String bearerSpeed;// Bearer_Speed__c
    private Integer serviceTerms;
    private String carrierCIR;//Carrier_CIR__c

    @SalesforceCollection(type = Radius.class)
    private List<Radius> radiuses = new ArrayList<>();

    private List<VLAN> vlans = new ArrayList<>();
    private List<VLAN> internationalVlans = new ArrayList<>();
    private List<InnerVLAN> innerVlans = new ArrayList<>();

    private Date inServiceDate;
    private Date contractEndDate; //Service_End_Date__c
    private Date created;

    private String carrierNNIId;

    private String carrierInterfaceBEnd; //Carrier_Interface_B_End__c

    private Double accessDistancePairs; //No_of_Pairs_Delivered__c"


    public Site getSiteAEnd() {
        return siteAEnd;
    }

    public void setSiteAEnd(Site siteAEnd) {
        this.siteAEnd = siteAEnd;
    }

    // required for csv
    public String getSiteNameAEnd() {
        return siteAEnd == null ? null : siteAEnd.getName();
    }

    // required for csv
    public String getSitePostCodeAEnd() {
        return siteAEnd == null ? null : siteAEnd.getPostCode();
    }

    // required for csv
    public String getSiteNameBEnd() {
        return siteBEnd == null ? null : siteBEnd.getName();
    }

    // required for csv
    public String getSitePostcodeBEnd() {
        return siteBEnd == null ? null : siteBEnd.getPostCode();
    }

    public Site getSiteBEnd() {
        return siteBEnd;
    }

    public void setSiteBEnd(Site siteBEnd) {
        this.siteBEnd = siteBEnd;
    }

    @Override
    protected Site getSite() {
        return getSiteBEnd();
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getInServiceDate() {
        return inServiceDate;
    }

    public void setInServiceDate(Date inServiceDate) {
        this.inServiceDate = inServiceDate;
    }

    public String getEndCustomerName() {
        return endCustomerName;
    }

    public void setEndCustomerName(String endCustomerName) {
        this.endCustomerName = endCustomerName;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBandwidthSold() {
        return bandwidthSold;
    }

    public void setBandwidthSold(String bandwidthSold) {
        this.bandwidthSold = bandwidthSold;
    }

    public String getCircuitID() {
        return circuitID;
    }

    public void setCircuitID(String circuitID) {
        this.circuitID = circuitID;
    }

    public String getBearerSpeed() {
        return bearerSpeed;
    }

    public void setBearerSpeed(String bearerSpeed) {
        this.bearerSpeed = bearerSpeed;
    }

    public List<Radius> getRadiuses() {
        return radiuses;
    }

    public void setRadiuses(List<Radius> radiuses) {
        this.radiuses = radiuses;
    }

    public List<VLAN> getVlans() {
        return vlans;
    }

    public void setVlans(List<VLAN> vlans) {
        this.vlans = vlans;
    }

    public List<VLAN> getInternationalVlans() {
        return internationalVlans;
    }

    public void setInternationalVlans(List<VLAN> internationalVlans) {
        this.internationalVlans = internationalVlans;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAssetBEndName() {
        return assetBEndName;
    }

    public void setAssetBEndName(String assetBEndName) {
        this.assetBEndName = assetBEndName;
    }

    public List<InnerVLAN> getInnerVlans() {
        return innerVlans;
    }

    public void setInnerVlans(List<InnerVLAN> innerVlans) {
        this.innerVlans = innerVlans;
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

    public String getFloorBEnd() {
        return floorBEnd;
    }

    public void setFloorBEnd(String floorBEnd) {
        this.floorBEnd = floorBEnd;
    }

    public String getRoomBEnd() {
        return roomBEnd;
    }

    public void setRoomBEnd(String roomBEnd) {
        this.roomBEnd = roomBEnd;
    }

    public String getRackBEnd() {
        return rackBEnd;
    }

    public void setRackBEnd(String rackBEnd) {
        this.rackBEnd = rackBEnd;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getAssetBEndId() {
        return assetBEndId;
    }

    public void setAssetBEndId(String assetBEndId) {
        this.assetBEndId = assetBEndId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderProductName() {
        return providerProductName;
    }

    public void setProviderProductName(String providerProductName) {
        this.providerProductName = providerProductName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCarrierCIR() {
        return carrierCIR;
    }

    public void setCarrierCIR(String carrierCIR) {
        this.carrierCIR = carrierCIR;
    }

    public Integer getCarrierContract() {
        return carrierContract;
    }

    public void setCarrierContract(Integer carrierContract) {
        this.carrierContract = carrierContract;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Double getAccessDistancePairs() {
        return accessDistancePairs;
    }

    public void setAccessDistancePairs(Double accessDistancePairs) {
        this.accessDistancePairs = accessDistancePairs;
    }

    public void setCarrierNNIId(String carrierNNIId) {
        this.carrierNNIId = carrierNNIId;
    }

    public String getCarrierNNIId() {
        return carrierNNIId;
    }

    public String getCarrierInterfaceBEnd() {
        return carrierInterfaceBEnd;
    }

    public void setCarrierInterfaceBEnd(String carrierInterfaceBEnd) {
        this.carrierInterfaceBEnd = carrierInterfaceBEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return true;

        Access access = (Access) o;
        return getRadiuses() != null ? !getRadiuses().equals(access.getRadiuses()) : access.getRadiuses() != null;
    }

}
