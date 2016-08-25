package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceCollection;
import uk.co.virtual1.salesforce.cache.SalesforceParentId;
import uk.co.virtual1.salesforce.cache.SalesforceRelation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Case extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    public enum SupportCaseStatus {
        OPEN("Open"),
        CLOSED("Closed"),
        UNREAD_CASE_COMMENT("Unread Case Comment");

        private String label;

        SupportCaseStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

    }

    private String caseNumber;
    private String accountId;
    private String accountName;
    private String endCustomerName;
    private String status;
    private String type;
    private String reason;
    private String priority;
    private boolean closed;
    private String subject;
    private String origin;
    private Date created;
    private Date provisionalRFS;
    private String caseOwner;
    private String netOpsOwner; //NetOps_Owner__c
    private Date orderAccepted; //Order_Accepted__c
    private Date orderReceived; //Order_Received__c
    private String leadTime; //Lead_Time_Range__c
    private String description;
    private boolean escalated;
    private boolean projectMgmtAllocated; // Project_Number1__c.Project_Mgmt_Allocated__c
    private String projectManager;
    private String deliverySchedule; //Delivery_Schedule__c

    private String siteSurveyResult; //Site_Survey_Results__c (picklist)

    //B-End
    private Date siteSurveyDate; // Site_Survey_Date__c
    private String siteSurveyTime; //Site_Survey_Time__c (picklist)

    private Date installationDate; // Installation_Date__c
    private String installationTime; //Installation_Time__c

    private Date endToEndTestDate; //End_to_End_Test_Date__c
    private String endToEndTestTime;//End_to_End_Test_Time__c

    private String siteNameBEnd; //Site_Name_B_End__c
    private String phoneBEnd; //Phone_B_End__c
    private String postCode; //Post_Code_B_End__c
    private String address; //Address_B_End__c

    //A-End
    private Date siteSurveyDateAEnd; // Site_Survey_Date_A_End__c
    private String siteSurveyTimeAEnd; //Site_Survey_Time_A_End__c

    private Date installationDateAEnd; //Installation_Date_A_End__c
    private String installationTimeAEnd; //Installation_Time_A_End__c

    private Date endToEndTestDateAEnd; //End_to_End_Test_Date_A_End__c
    private String endToEndTestTimeAEnd;//End_to_End_Test_Time_A_End__c

    private Date ECCsAcceptanceDeadline;

    private String virtual1EngineerInstallRqd;//Virtual1_Engineer_Install_Rqd__c

    private Date qubeInstallDate; //Qube_Install_Date__c
    private String qubeInstallSlot;//Qube_Install_Slot__c

    private Date analogueCommittedDate;//Analogue_Committed_Date__c
    private Date analogueOrderAcceptedDate;//Analogue_Order_Accepted_Date__c
    private Date analogueInstallationDate; //Analogue_Installation_Date__c
    private String analogueInstallationTime; //Analogue_Installation_Time__c


    private boolean deskTopSurveyCompleted;//Desk_Top_Survey_Completed__c
    private Date deskTopSurveyDate;//Desk_Top_Survey_Date__c

    private String reference; //Partner_Internal_Ref__c

    private String carrierProvider; //Carrier_Provider__c

    @SalesforceRelation
    private Access access;
    @SalesforceRelation
    private Asset assetAEnd;
    @SalesforceRelation
    private Asset assetBEnd;
    @SalesforceRelation
    private Contact contact;

    private Date estimatedCompletionDate;  //Estimated_Completion_Date__c
    private Date nextUpdateDue;  //Next_Update_Due__c
    private Date inServiceDate;

    @SalesforceCollection(type = CaseComment.class)
    private List<CaseComment> caseComments = new ArrayList<>();
    @SalesforceCollection(type = CaseContactRole.class)
    private List<CaseContactRole> caseContactRoles = new ArrayList<>();

    private Date accessAvailableDateBEnd; //Site_Access_Available_Date_B_End__с
    private String siteConstrainsBEnd; //Site_Constraints_B_End__c
    private String siteStatusBEnd; //Site_Status_B_End__c

    private String siteNotes; //Site_Notes_B_End__c

    private String carrierOrderReference; //Carrier_Order_Reference__c
    private String carrierAppointmentReference; //Carrier_Appointment_Reference__c
    private Calendar carrierAppointmentDate; //Carrier_Appointment_Date__c
    private String carrierAppointmentTimeslot; //Carrier_Appointment_Timeslot__c

    private String minimumGuaranteedSpeed; // MinimumGuaranteedSpeed__c

    private boolean allowBookingAppointment = false;

    @SalesforceParentId
    private String projectId; // Project_Number1__c

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the endCustomerName
     */
    public String getEndCustomerName() {
        return endCustomerName;
    }

    /**
     * @param endCustomerName the endCustomerName to set
     */
    public void setEndCustomerName(String endCustomerName) {
        this.endCustomerName = endCustomerName;
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
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the caseNumber
     */
    public String getCaseNumber() {
        return caseNumber;
    }

    /**
     * @param caseNumber the caseNumber to set
     */
    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
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

    public List<CaseComment> getCaseComments() {
        return caseComments;
    }

    public void setCaseComments(List<CaseComment> caseComments) {
        this.caseComments = caseComments;
    }

    /**
     * @return the caseOwner
     */
    public String getCaseOwner() {
        return caseOwner;
    }

    /**
     * @param caseOwner the caseOwner to set
     */
    public void setCaseOwner(String caseOwner) {
        this.caseOwner = caseOwner;
    }

    /**
     * @return the access
     */
    public Access getAccess() {
        if (access == null) {
            access = new Access();
        }
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(Access access) {
        this.access = access;
    }

    /**
     * @return the assetAEnd
     */
    public Asset getAssetAEnd() {
        if (assetAEnd == null) {
            assetAEnd = new Asset();
        }
        return assetAEnd;
    }

    /**
     * @param assetAEnd the assetAEnd to set
     */
    public void setAssetAEnd(Asset assetAEnd) {
        this.assetAEnd = assetAEnd;
    }

    /**
     * @return the assetBEnd
     */
    public Asset getAssetBEnd() {
        if (assetBEnd == null) {
            assetBEnd = new Asset();
        }
        return assetBEnd;
    }

    /**
     * @param assetBEnd the assetBEnd to set
     */
    public void setAssetBEnd(Asset assetBEnd) {
        this.assetBEnd = assetBEnd;
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        if (contact == null) {
            contact = new Contact();
        }
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the provisionalRFS
     */
    public Date getProvisionalRFS() {
        return provisionalRFS;
    }

    /**
     * @param provisionalRFS the provisionalRFS to set
     */
    public void setProvisionalRFS(Date provisionalRFS) {
        this.provisionalRFS = provisionalRFS;
    }

    /**
     * @return the orderAccepted
     */
    public Date getOrderAccepted() {
        return orderAccepted;
    }

    /**
     * @param orderAccepted the orderAccepted to set
     */
    public void setOrderAccepted(Date orderAccepted) {
        this.orderAccepted = orderAccepted;
    }

    /**
     * @return the leadTime
     */
    public String getLeadTime() {
        return leadTime;
    }

    /**
     * @param leadTime the leadTime to set
     */
    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getAccessSiteName() {
        return getAccess().getSiteName();
    }

    public String getAccessServiceType() {
        return getAccess().getServiceType();
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the escalated
     */
    public boolean isEscalated() {
        return escalated;
    }

    /**
     * @param escalated the escalated to set
     */
    public void setEscalated(boolean escalated) {
        this.escalated = escalated;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getNetOpsOwner() {
        return netOpsOwner;
    }

    public void setNetOpsOwner(String netOpsOwner) {
        this.netOpsOwner = netOpsOwner;
    }

    public Date getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(Date orderReceived) {
        this.orderReceived = orderReceived;
    }

    public boolean isProjectMgmtAllocated() {
        return projectMgmtAllocated;
    }

    public void setProjectMgmtAllocated(boolean projectMgmtAllocated) {
        this.projectMgmtAllocated = projectMgmtAllocated;
    }

    public String getDeliverySchedule() {
        return deliverySchedule;
    }

    public void setDeliverySchedule(String deliverySchedule) {
        this.deliverySchedule = deliverySchedule;
    }

    public Date getSiteSurveyDate() {
        return siteSurveyDate;
    }

    public void setSiteSurveyDate(Date siteSurveyDate) {
        this.siteSurveyDate = siteSurveyDate;
    }

    public String getSiteSurveyTime() {
        return siteSurveyTime;
    }

    public void setSiteSurveyTime(String siteSurveyTime) {
        this.siteSurveyTime = siteSurveyTime;
    }

    public String getSiteSurveyResult() {
        return siteSurveyResult;
    }

    public void setSiteSurveyResult(String siteSurveyResult) {
        this.siteSurveyResult = siteSurveyResult;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public Date getInstallationDateAEnd() {
        return installationDateAEnd;
    }

    public void setInstallationDateAEnd(Date installationDateAEnd) {
        this.installationDateAEnd = installationDateAEnd;
    }

    public Date getInstallationDateBEnd() {
        return installationDate;//backward compatibility
    }

    public Date getEndToEndTestDate() {
        return endToEndTestDate;
    }

    public void setEndToEndTestDate(Date endToEndTestDate) {
        this.endToEndTestDate = endToEndTestDate;
    }

    public Date getECCsAcceptanceDeadline() {
        return ECCsAcceptanceDeadline;
    }

    public void setECCsAcceptanceDeadline(Date eCCsAcceptanceDeadline) {
        ECCsAcceptanceDeadline = eCCsAcceptanceDeadline;
    }

    public String getInstallationTime() {
        return installationTime;
    }

    public void setInstallationTime(String installationTime) {
        this.installationTime = installationTime;
    }

    public String getEndToEndTestTime() {
        return endToEndTestTime;
    }

    public void setEndToEndTestTime(String endToEndTestTime) {
        this.endToEndTestTime = endToEndTestTime;
    }

    public String getVirtual1EngineerInstallRqd() {
        return virtual1EngineerInstallRqd;
    }

    public void setVirtual1EngineerInstallRqd(String virtual1EngineerInstallRqd) {
        this.virtual1EngineerInstallRqd = virtual1EngineerInstallRqd;
    }

    public Date getQubeInstallDate() {
        return qubeInstallDate;
    }

    public void setQubeInstallDate(Date qubeInstallDate) {
        this.qubeInstallDate = qubeInstallDate;
    }

    public String getQubeInstallSlot() {
        return qubeInstallSlot;
    }

    public void setQubeInstallSlot(String qubeInstallSlot) {
        this.qubeInstallSlot = qubeInstallSlot;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Date getAnalogueCommittedDate() {
        return analogueCommittedDate;
    }

    public void setAnalogueCommittedDate(Date analogueCommittedDate) {
        this.analogueCommittedDate = analogueCommittedDate;
    }

    public Date getAnalogueInstallationDate() {
        return analogueInstallationDate;
    }

    public void setAnalogueInstallationDate(Date analogueInstallationDate) {
        this.analogueInstallationDate = analogueInstallationDate;
    }

    public String getAnalogueInstallationTime() {
        return analogueInstallationTime;
    }

    public void setAnalogueInstallationTime(String analogueInstallationTime) {
        this.analogueInstallationTime = analogueInstallationTime;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCarrierProvider() {
        return carrierProvider;
    }

    public void setCarrierProvider(String carrierProvider) {
        this.carrierProvider = carrierProvider;
    }

    public Date getAnalogueOrderAcceptedDate() {
        return analogueOrderAcceptedDate;
    }

    public void setAnalogueOrderAcceptedDate(Date analogueOrderAcceptedDate) {
        this.analogueOrderAcceptedDate = analogueOrderAcceptedDate;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }


    public Date getInServiceDate() {
        return inServiceDate;
    }

    public void setInServiceDate(Date inServiceDate) {
        this.inServiceDate = inServiceDate;
    }

    public Date getSiteSurveyDateAEnd() {
        return siteSurveyDateAEnd;
    }

    public void setSiteSurveyDateAEnd(Date siteSurveyDateAEnd) {
        this.siteSurveyDateAEnd = siteSurveyDateAEnd;
    }

    public String getSiteSurveyTimeAEnd() {
        return siteSurveyTimeAEnd;
    }

    public void setSiteSurveyTimeAEnd(String siteSurveyTimeAEnd) {
        this.siteSurveyTimeAEnd = siteSurveyTimeAEnd;
    }

    public String getInstallationTimeAEnd() {
        return installationTimeAEnd;
    }

    public void setInstallationTimeAEnd(String installationTimeAEnd) {
        this.installationTimeAEnd = installationTimeAEnd;
    }

    public Date getEndToEndTestDateAEnd() {
        return endToEndTestDateAEnd;
    }

    public void setEndToEndTestDateAEnd(Date endToEndTestDateAEnd) {
        this.endToEndTestDateAEnd = endToEndTestDateAEnd;
    }

    public String getEndToEndTestTimeAEnd() {
        return endToEndTestTimeAEnd;
    }

    public void setEndToEndTestTimeAEnd(String endToEndTestTimeAEnd) {
        this.endToEndTestTimeAEnd = endToEndTestTimeAEnd;
    }

    public boolean isDeskTopSurveyCompleted() {
        return deskTopSurveyCompleted;
    }

    public void setDeskTopSurveyCompleted(boolean deskTopSurveyCompleted) {
        this.deskTopSurveyCompleted = deskTopSurveyCompleted;
    }

    public Date getDeskTopSurveyDate() {
        return deskTopSurveyDate;
    }

    public void setDeskTopSurveyDate(Date deskTopSurveyDate) {
        this.deskTopSurveyDate = deskTopSurveyDate;
    }

    @Deprecated // use getSiteNameBEnd instead
    public String getSiteName() {
        return getSiteNameBEnd();
    }

    public String getSiteNameBEnd() {
        return siteNameBEnd;
    }

    public void setSiteNameBEnd(String siteNameBEnd) {
        this.siteNameBEnd = siteNameBEnd;
    }

    public String getPhoneBEnd() {
        return phoneBEnd;
    }

    public void setPhoneBEnd(String phoneBEnd) {
        this.phoneBEnd = phoneBEnd;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEstimatedCompletionDate() {
        return estimatedCompletionDate;
    }

    public void setEstimatedCompletionDate(Date estimatedCompletionDate) {
        this.estimatedCompletionDate = estimatedCompletionDate;
    }

    public Date getNextUpdateDue() {
        return nextUpdateDue;
    }

    public void setNextUpdateDue(Date nextUpdateDue) {
        this.nextUpdateDue = nextUpdateDue;
    }

    public List<CaseContactRole> getCaseContactRoles() {
        return caseContactRoles;
    }

    public void setCaseContactRoles(List<CaseContactRole> caseContactRoles) {
        this.caseContactRoles = caseContactRoles;
    }

    public Date getAccessAvailableDateBEnd() {
        return accessAvailableDateBEnd;
    }

    public void setAccessAvailableDateBEnd(Date accessAvailableDateBEnd) {
        this.accessAvailableDateBEnd = accessAvailableDateBEnd;
    }

    public String getSiteConstrainsBEnd() {
        return siteConstrainsBEnd;
    }

    public void setSiteConstrainsBEnd(String siteConstrainsBEnd) {
        this.siteConstrainsBEnd = siteConstrainsBEnd;
    }

    public String getSiteStatusBEnd() {
        return siteStatusBEnd;
    }

    public void setSiteStatusBEnd(String siteStatusBEnd) {
        this.siteStatusBEnd = siteStatusBEnd;
    }

    public String getSiteNotes() {
        return siteNotes;
    }

    public void setSiteNotes(String siteNotes) {
        this.siteNotes = siteNotes;
    }

    public String getCarrierOrderReference() {
        return carrierOrderReference;
    }

    public void setCarrierOrderReference(String carrierOrderReference) {
        this.carrierOrderReference = carrierOrderReference;
    }

    public String getCarrierAppointmentReference() {
        return carrierAppointmentReference;
    }

    public void setCarrierAppointmentReference(String carrierAppointmentReference) {
        this.carrierAppointmentReference = carrierAppointmentReference;
    }

    public Calendar getCarrierAppointmentDate() {
        return carrierAppointmentDate;
    }

    public void setCarrierAppointmentDate(Calendar carrierAppointmentDate) {
        this.carrierAppointmentDate = carrierAppointmentDate;
    }

    public String getCarrierAppointmentTimeslot() {
        return carrierAppointmentTimeslot;
    }

    public void setCarrierAppointmentTimeslot(String carrierAppointmentTimeslot) {
        this.carrierAppointmentTimeslot = carrierAppointmentTimeslot;
    }

    public boolean isAllowBookingAppointment() {
        return allowBookingAppointment;
    }

    public void setAllowBookingAppointment(boolean allowBookingAppointment) {
        this.allowBookingAppointment = allowBookingAppointment;
    }

    public String getMinimumGuaranteedSpeed() {
        return minimumGuaranteedSpeed;
    }

    public void setMinimumGuaranteedSpeed(String minimumGuaranteedSpeed) {
        this.minimumGuaranteedSpeed = minimumGuaranteedSpeed;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        if (caseNumber != null) builder.append(" caseNumber=").append(caseNumber);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Case)) return false;
        if (!super.equals(o)) return false;

        return hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * getCaseComments().hashCode() + 31 * getCaseContactRoles().hashCode();
    }

}
