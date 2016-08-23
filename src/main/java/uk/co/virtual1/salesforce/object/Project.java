package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceCollection;
import uk.co.virtual1.salesforce.cache.SalesforceRelation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private String projectNumber; //Project_Number__c
    private String specialInstructions;

    private String customerSignedBy;
    private Date customerSignedDate;
    private String customerSignedTitle;

    private String projectStatusDel;

    private Boolean portalOrderFullyLoaded; //Portal_Order_Fully_Loaded__c
    // Project Details
    private String provisioningOwner;
    private String netOpsOwner;
    private String projectType;
    private String projectStatus;
    private boolean projectMgmtAllocated;
    private String projectManager;

    @SalesforceRelation
    private Account account = new Account();
    @SalesforceRelation
    private EndCustomer endCustomer = new EndCustomer();
    @SalesforceRelation
    private Contact contact = new Contact();

    @SalesforceCollection(type = Asset.class)
    private List<Asset> assets = new ArrayList<>();
    @SalesforceCollection(type = Access.class)
    private List<Access> accesses = new ArrayList<>();
    @SalesforceCollection(type = Component.class)
    private List<Component> components = new ArrayList<>();
    @SalesforceCollection(type = Case.class)
    private List<Case> cases = new ArrayList<>();

    @Deprecated
    public String getProjectId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public EndCustomer getEndCustomer() {
        return endCustomer;
    }

    public void setEndCustomer(EndCustomer endCustomer) {
        this.endCustomer = endCustomer;
    }

    public String getProvisioningOwner() {
        return provisioningOwner;
    }

    public void setProvisioningOwner(String provisioningOwner) {
        this.provisioningOwner = provisioningOwner;
    }

    public String getNetOpsOwner() {
        return netOpsOwner;
    }

    public void setNetOpsOwner(String netOpsOwner) {
        this.netOpsOwner = netOpsOwner;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public boolean isProjectMgmtAllocated() {
        return projectMgmtAllocated;
    }

    public void setProjectMgmtAllocated(boolean projectMgmtAllocated) {
        this.projectMgmtAllocated = projectMgmtAllocated;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getCustomerSignedBy() {
        return customerSignedBy;
    }

    public void setCustomerSignedBy(String customerSignedBy) {
        this.customerSignedBy = customerSignedBy;
    }

    public Date getCustomerSignedDate() {
        return customerSignedDate;
    }

    public void setCustomerSignedDate(Date customerSignedDate) {
        this.customerSignedDate = customerSignedDate;
    }

    public String getCustomerSignedTitle() {
        return customerSignedTitle;
    }

    public void setCustomerSignedTitle(String customerSignedTitle) {
        this.customerSignedTitle = customerSignedTitle;
    }

    public String getProjectStatusDel() {
        return projectStatusDel;
    }

    public void setProjectStatusDel(String projectStatusDel) {
        this.projectStatusDel = projectStatusDel;
    }

    public Boolean getPortalOrderFullyLoaded() {
        return portalOrderFullyLoaded;
    }

    public void setPortalOrderFullyLoaded(Boolean portalOrderFullyLoaded) {
        this.portalOrderFullyLoaded = portalOrderFullyLoaded;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Access> getAccesses() {
        return accesses;
    }

    public void setAccesses(List<Access> accesses) {
        this.accesses = accesses;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

}
