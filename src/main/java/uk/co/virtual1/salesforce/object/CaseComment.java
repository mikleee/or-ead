package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceParentId;

public class CaseComment extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private String body;
    private String creatorName;
    private boolean published;
    @SalesforceParentId
    private String caseId;

    public CaseComment() {
    }

    public CaseComment(String caseId, String body, boolean published) {
        this.caseId = caseId;
        this.body = body;
        this.published = published;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        if (caseId != null) builder.append("caseId=").append(caseId).append(" ");
        if (body != null) builder.append("body=").append(body);
        return builder.toString();
    }
}
