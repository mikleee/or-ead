package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceRelation;

public class Site extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private String address;
    private String phone;
    private String postCode;
    private String siteContact;
    private String unitBuildingNumber;
    private String buildingName;
    private String streetNumber;
    private String streetName;
    private String townCity;
    private String county;
    private String addressRef;
    private String districtCode;
    private String qualifier;
    private String buildConstructedBefore;
    private String asbestos;
    @SalesforceRelation
    private EndCustomer endCustomer = new EndCustomer();

    public EndCustomer getEndCustomer() {
        return endCustomer;
    }

    public void setEndCustomer(EndCustomer endCustomer) {
        this.endCustomer = endCustomer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSiteContact() {
        return siteContact;
    }

    public void setSiteContact(String siteContact) {
        this.siteContact = siteContact;
    }

    public String getBuildConstructedBefore() {
        return buildConstructedBefore;
    }

    public void setBuildConstructedBefore(String buildConstructedBefore) {
        this.buildConstructedBefore = buildConstructedBefore;
    }

    public String getAsbestos() {
        return asbestos;
    }

    public void setAsbestos(String asbestos) {
        this.asbestos = asbestos;
    }

    public String getUnitBuildingNumber() {
        return unitBuildingNumber;
    }

    public void setUnitBuildingNumber(String unitBuildingNumber) {
        this.unitBuildingNumber = unitBuildingNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressRef() {
        return addressRef;
    }

    public void setAddressRef(String addressRef) {
        this.addressRef = addressRef;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
}
