package uk.co.virtual1.salesforce.object;


import uk.co.virtual1.salesforce.cache.SalesforceParentId;
import uk.co.virtual1.salesforce.cache.SalesforceRelation;

public class CaseContactRole extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    @SalesforceParentId
    private String caseId;
    private String role;
    private String isDeleted;
    @SalesforceRelation
    private Contact contact = new Contact();

    public CaseContactRole() {
    }

    public CaseContactRole(String caseId, String contactId, String role) {
        this.caseId = caseId;
        this.role = role;
        this.contact.setId(contactId);
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String isDeleted() {
        return isDeleted;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        if (caseId != null) builder.append("caseId=").append(caseId).append(" ");
        if (role != null) builder.append("role=").append(role).append(" ");
        if (contact.getId() != null) builder.append("contactId=").append(contact.getId());
        return builder.toString();
    }
}
