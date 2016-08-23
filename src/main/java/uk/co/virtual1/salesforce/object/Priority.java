package uk.co.virtual1.salesforce.object;

public enum Priority {

    N_A("1 - N/A"),
    SERVICE_DOWN("1 - Service Down"),
    FIREWALL("2 - Firewall"),
    SERVICE_DEGRADED("2 - Service Degraded"),
    REPEAT_ISSUE("2 - Repeat Issue"),
    LOW("3 - Low"),
    NON_SERVICE_AFFECTING("4 - Non-service affecting");

    private String description;

    private Priority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
