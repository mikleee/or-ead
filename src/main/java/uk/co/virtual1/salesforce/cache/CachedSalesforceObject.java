package uk.co.virtual1.salesforce.cache;

import java.io.Serializable;

/**
 * @author Mikhail Tkachenko created on 20.08.16 12:32
 */
public abstract class CachedSalesforceObject implements Serializable {
    private boolean isExpired = false;

    public abstract String getId();

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired() {
        isExpired = true;
    }


}
