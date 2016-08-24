package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceRelation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SfIpJust extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private Long solutionId;
    private String postCode;
    private String projectId; //ProjectId__c
    private String projectNumber;
    private String status; //Status__c, Text 50, Mandatory
    private String registeredCompanyId; //Text 150, Mandatory
    private String registeredCompanyName; //Text 150, Mandatory
    private String companyAddress; //CompanyAddress__c , Text 500, Mandatory
    private String companyWebsite; //CompanyWebsite__c , Text 225, Mandatory
    private String adminContactName; //AdminContactName__c , Text 150, Mandatory
    private String adminContactPostalAddress; //AdminContactPostalAddress__c , Text 500, Mandatory
    private String adminContactPhone; //AdminContactPhone__c , Text 15, Mandatory
    private String adminContactEmailAddress; //AdminContactEmailAddress__c , Text 255, Mandatory
    private String adminContactNichdl; //AdminContactNichdl__c , Text 255, Mandatory
    private String abuseContact; //AbuseContact__c , Text 255, Mandatory
    private Date detailRecordLastModifiedDate; //DetailRecordLastModifiedDate__c , DateTime
    private String subnetGranted; //SubnetGranted__c , Text 15,
    private SfAttachment networkDiagram;
    private String subnetName; //SubnetName__c , Text 255, Mandatory
    private String briefDescription; //BriefDescription__c , Text 500, Mandatory
    private List<SFIPJustDevice> sfipJustDevices = new ArrayList<>();

    @SalesforceRelation
    private Account partner = new Account();
    @SalesforceRelation
    private Contact contact = new Contact();


    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisteredCompanyId() {
        return registeredCompanyId;
    }

    public void setRegisteredCompanyId(String registeredCompanyId) {
        this.registeredCompanyId = registeredCompanyId;
    }

    public String getRegisteredCompanyName() {
        return registeredCompanyName;
    }

    public void setRegisteredCompanyName(String registeredCompanyName) {
        this.registeredCompanyName = registeredCompanyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getAdminContactName() {
        return adminContactName;
    }

    public void setAdminContactName(String adminContactName) {
        this.adminContactName = adminContactName;
    }

    public String getAdminContactPostalAddress() {
        return adminContactPostalAddress;
    }

    public void setAdminContactPostalAddress(String adminContactPostalAddress) {
        this.adminContactPostalAddress = adminContactPostalAddress;
    }

    public String getAdminContactPhone() {
        return adminContactPhone;
    }

    public void setAdminContactPhone(String adminContactPhone) {
        this.adminContactPhone = adminContactPhone;
    }

    public String getAdminContactEmailAddress() {
        return adminContactEmailAddress;
    }

    public void setAdminContactEmailAddress(String adminContactEmailAddress) {
        this.adminContactEmailAddress = adminContactEmailAddress;
    }

    public String getAdminContactNichdl() {
        return adminContactNichdl;
    }

    public void setAdminContactNichdl(String adminContactNichdl) {
        this.adminContactNichdl = adminContactNichdl;
    }

    public String getAbuseContact() {
        return abuseContact;
    }

    public void setAbuseContact(String abuseContact) {
        this.abuseContact = abuseContact;
    }

    public Date getDetailRecordLastModifiedDate() {
        return detailRecordLastModifiedDate;
    }

    public void setDetailRecordLastModifiedDate(Date detailRecordLastModifiedDate) {
        this.detailRecordLastModifiedDate = detailRecordLastModifiedDate;
    }

    public SfAttachment getNetworkDiagram() {
        return networkDiagram;
    }

    public void setNetworkDiagram(SfAttachment networkDiagram) {
        this.networkDiagram = networkDiagram;
    }

    public String getSubnetGranted() {
        return subnetGranted;
    }

    public void setSubnetGranted(String subnetGranted) {
        this.subnetGranted = subnetGranted;
    }

    public String getSubnetName() {
        return subnetName;
    }

    public void setSubnetName(String subnetName) {
        this.subnetName = subnetName;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public List<SFIPJustDevice> getSfipJustDevices() {
        return sfipJustDevices;
    }

    public void setSfipJustDevices(List<SFIPJustDevice> sfipJustDevices) {
        this.sfipJustDevices = sfipJustDevices;
    }

    public Account getPartner() {
        return partner;
    }

    public void setPartner(Account partner) {
        this.partner = partner;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        SfIpJust sfIpJust = (SfIpJust) o;
        if (getLastModifiedDate() != null ? !getLastModifiedDate().equals(sfIpJust.getLastModifiedDate()) : sfIpJust.getLastModifiedDate() != null)
            return false;
        if (getNetworkDiagram() != null ? !getNetworkDiagram().equals(sfIpJust.getNetworkDiagram()) : sfIpJust.getNetworkDiagram() != null)
            return false;
        return true;
    }

}
