package uk.co.virtual1.salesforce.object;

import java.util.List;

public class AccountPbt extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private PbtValue status;
    private List<String> values;
    private String pbt;

    public enum PbtValue {
        NONE, SELECTED
    }

    public PbtValue getStatus() {
        return status;
    }

    public void setStatus(PbtValue status) {
        this.status = status;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String selectedValue) {
        this.pbt = selectedValue;
    }
}
