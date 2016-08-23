package uk.co.virtual1.salesforce.object;

import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class Account extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private String oneViewUsername;
    private String oneViewPassword;
    private String accountOwnerId;
    private String accountOwnerName;
    private String accountOwnerEmail;
    private String accountOwnerMobileNumber;
    private boolean poNumberRequired;
    private String billingStreet;
    private String billingCity;
    private String billingState;
    private String billingPostalCode;
    private String billingCountry;
    private String shippingStreet;
    private String shippingCity;
    private String shippingState;
    private String shippingPostalCode;
    private String shippingCountry;
    private String companyName;
    private String coReg;
    private String salesOrderSpecialInstructions;
    private String coeApproved;
    private String ridNumber;
    private Date X1CloudDemoRequestBlackout; //X1Cloud_Demo_Request_Blackout__c
    private String status;
    private String pbt;


    /**
     * @return the oneViewUsername
     */
    public String getOneViewUsername() {
        return oneViewUsername;
    }

    /**
     * @param oneViewUsername the oneViewUsername to set
     */
    public void setOneViewUsername(String oneViewUsername) {
        this.oneViewUsername = oneViewUsername;
    }

    /**
     * @return the oneViewPassword
     */
    public String getOneViewPassword() {
        return oneViewPassword;
    }

    /**
     * @param oneViewPassword the oneViewPassword to set
     */
    public void setOneViewPassword(String oneViewPassword) {
        this.oneViewPassword = oneViewPassword;
    }

    public String getAccountOwnerId() {
        return accountOwnerId;
    }

    public void setAccountOwnerId(String accountOwnerId) {
        this.accountOwnerId = accountOwnerId;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public String getAccountOwnerEmail() {
        return accountOwnerEmail;
    }

    public void setAccountOwnerEmail(String accountOwnerEmail) {
        this.accountOwnerEmail = accountOwnerEmail;
    }

    public String getAccountOwnerMobileNumber() {
        return accountOwnerMobileNumber;
    }

    public void setAccountOwnerMobileNumber(String accountOwnerMobileNumber) {
        this.accountOwnerMobileNumber = accountOwnerMobileNumber;
    }

    public boolean isPoNumberRequired() {
        return poNumberRequired;
    }

    public void setPoNumberRequired(boolean poNumberRequired) {
        this.poNumberRequired = poNumberRequired;
    }

    public String getShippingStreet() {
        return shippingStreet;
    }

    public void setShippingStreet(String shippingStreet) {
        this.shippingStreet = shippingStreet;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCoReg() {
        return coReg;
    }

    public void setCoReg(String coReg) {
        this.coReg = coReg;
    }

    public String getSalesOrderSpecialInstructions() {
        return salesOrderSpecialInstructions;
    }

    public void setSalesOrderSpecialInstructions(
            String salesOrderSpecialInstructions) {
        this.salesOrderSpecialInstructions = salesOrderSpecialInstructions;
    }

    public String getCoeApproved() {
        return coeApproved;
    }

    public void setCoeApproved(String coeApproved) {
        this.coeApproved = coeApproved;
    }

    public boolean isCOE() {
        return coeApproved != null && "Virtual1 Network Only".equalsIgnoreCase(coeApproved);
    }

    public String getRidNumber() {
        return ridNumber;
    }

    public void setRidNumber(String ridNumber) {
        this.ridNumber = ridNumber;
    }

    public Date getX1CloudDemoRequestBlackout() {
        return X1CloudDemoRequestBlackout;
    }

    public void setX1CloudDemoRequestBlackout(Date x1CloudDemoRequestBlackout) {
        X1CloudDemoRequestBlackout = x1CloudDemoRequestBlackout;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public boolean isActive() {
        return "Active".equals(status);
    }

    /**
     * is the billing address (shipping address in salesforce) empty
     */
    public boolean isBillingAddressMissing() {
        return StringUtils.isBlank(shippingStreet) &&
                StringUtils.isBlank(shippingCity) &&
                StringUtils.isBlank(shippingPostalCode) &&
                StringUtils.isBlank(shippingState) &&
                StringUtils.isBlank(shippingCountry);
    }
}
