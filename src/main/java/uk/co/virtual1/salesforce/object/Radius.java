package uk.co.virtual1.salesforce.object;

import uk.co.virtual1.salesforce.cache.SalesforceParentId;

public class Radius extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private String LANIPv4Gateway;
    private String vpnName;
    private String vpnType;
    private String username;
    private String password;
    private String subnetMask;

    @SalesforceParentId
    private String accessId;
    private String NNI__c;
    private String VPN__c;


    public String getLANIPv4Gateway() {
        return LANIPv4Gateway;
    }

    public void setLANIPv4Gateway(String lANIPv4Gateway) {
        LANIPv4Gateway = lANIPv4Gateway;
        subnetMask = SubnetIPMask.getMask(lANIPv4Gateway);
    }

    public String getVpnName() {
        return vpnName;
    }

    public void setVpnName(String vpnName) {
        this.vpnName = vpnName;
    }

    public String getVpnType() {
        return vpnType;
    }

    public void setVpnType(String vpnType) {
        this.vpnType = vpnType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getNNI__c() {
        return NNI__c;
    }

    public void setNNI__c(String NNI__c) {
        this.NNI__c = NNI__c;
    }

    public String getVPN__c() {
        return VPN__c;
    }

    public void setVPN__c(String VPN__c) {
        this.VPN__c = VPN__c;
    }

}