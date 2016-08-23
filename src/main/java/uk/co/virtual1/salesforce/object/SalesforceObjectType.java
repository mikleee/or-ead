package uk.co.virtual1.salesforce.object;

public enum SalesforceObjectType {

    Access("access"),
    Asset("asset"),
    Component("component");

    SalesforceObjectType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return this.value;
    }

    public static SalesforceObjectType defineByValue(String value) {
        SalesforceObjectType result = null;

        if (value == null) {
            return null;
        }

        if (value.equals(Access.getValue())) {
            result = Access;
        } else if (value.equals(Asset.getValue())) {
            result = Asset;
        } else if (value.equals(Component.getValue())) {
            result = Component;
        }

        return result;
    }

}
