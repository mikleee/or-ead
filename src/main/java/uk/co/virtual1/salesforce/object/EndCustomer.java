package uk.co.virtual1.salesforce.object;

public class EndCustomer extends BaseSalesforceObject {
    private String companyRegistration;
    private String accountId;
    private String prtgLogin;
    private String prtgPassword;

    public String getCompanyRegistration() {
        return companyRegistration;
    }

    public void setCompanyRegistration(String companyRegistration) {
        this.companyRegistration = companyRegistration;
    }

    public String getPrtgLogin() {
        return prtgLogin;
    }

    public void setPrtgLogin(String prtgLogin) {
        this.prtgLogin = prtgLogin;
    }

    public String getPrtgPassword() {
        return prtgPassword;
    }

    public void setPrtgPassword(String prtgPassword) {
        this.prtgPassword = prtgPassword;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

}
