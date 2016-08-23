package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.CachedSalesforceObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseSalesforceObject extends CachedSalesforceObject {
    private static final long serialVersionUID = 1L;

    protected String id; // Id
    protected String name; // Name
    protected Date createdDate; // CreatedDate
    protected Date lastModifiedDate; // LastModifiedDate

    private String recordTypeId;
    private String recordType;

    private final Map<String, Object> customFields = new HashMap<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecordType() {
        return recordType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordTypeId() {
        return recordTypeId;
    }

    public void setRecordTypeId(String recordTypeId) {
        this.recordTypeId = recordTypeId;
    }

    public Map<String, Object> getCustomFields() {
        return customFields;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append(getClass().getSimpleName()).append(": ");
        if (id != null) builder.append("id=").append(id).append(" ");
        if (name != null) builder.append("name=").append(name).append(" ");
        if (recordType != null) builder.append("recordType=").append(recordType);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseSalesforceObject)) return false;

        BaseSalesforceObject that = (BaseSalesforceObject) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getLastModifiedDate() != null ? getLastModifiedDate().equals(that.getLastModifiedDate()) : that.getLastModifiedDate() == null;

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
