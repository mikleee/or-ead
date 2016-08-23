package uk.co.virtual1.salesforce.object;

public class VLAN extends BaseSalesforceObject {

    private static final long serialVersionUID = 1L;

    private String CIR;
    private String LANIPv4Gateway;
    private String vpnName;
    private String vpnType;
    private String endCustomerName;
    private String endCustomerId;
    private String relatedLANPort;
    private String subnetMask;

    public String getCIR() {
        return CIR;
    }

    public void setCIR(String cIR) {
        CIR = cIR;
    }

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

    public String getEndCustomerName() {
        return endCustomerName;
    }

    public void setEndCustomerName(String endCustomerName) {
        this.endCustomerName = endCustomerName;
    }

    public String getEndCustomerId() {
        return endCustomerId;
    }

    public void setEndCustomerId(String endCustomerId) {
        this.endCustomerId = endCustomerId;
    }

    public String getRelatedLANPort() {
        return relatedLANPort;
    }

    public void setRelatedLANPort(String relatedLANPort) {
        this.relatedLANPort = relatedLANPort;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

}