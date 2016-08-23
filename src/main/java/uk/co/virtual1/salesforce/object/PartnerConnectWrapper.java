package uk.co.virtual1.salesforce.object;


public class PartnerConnectWrapper {

    private String name;

    private String siteName; //Site_Name_B_End__r.Name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
