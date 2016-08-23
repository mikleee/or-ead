package uk.co.virtual1.salesforce.object;

/**
 * @author Mikhail Tkachenko created on 17.08.16 15:05
 */
abstract class ChildObject extends BaseSalesforceObject {
    String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        if (parentId != null) builder.append("parentId=").append(parentId);
        return builder.toString();
    }

}
