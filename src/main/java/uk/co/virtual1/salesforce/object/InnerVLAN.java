package uk.co.virtual1.salesforce.object;

public class InnerVLAN extends BaseSalesforceObject {

    private static final long serialVersionUID = 1L;
    
    private String CIR;
    private String LANIPv4Gateway;
    private String vpnName;
    private String vpnType;
    private String outerVLANName;
    private String relatedLANPort;
    private String subnetMask;
    private String accessName; //Access_ID__c


    public String getCIR() {
        return CIR;
    }

    public void setCIR(String cIR) {
        CIR = cIR;
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
    
    public String getOuterVLANName() {
		return outerVLANName;
	}

	public void setOuterVLANName(String outerVLANName) {
		this.outerVLANName = outerVLANName;
	}
	
	public String getLANIPv4Gateway() {
		return LANIPv4Gateway;
	}

    public String getRelatedLANPort() {
        return relatedLANPort;
    }

    public void setRelatedLANPort(String relatedLANPort) {
        this.relatedLANPort = relatedLANPort;
    }

    public void setLANIPv4Gateway(String lANIPv4Gateway) {
		LANIPv4Gateway = lANIPv4Gateway;
        subnetMask = SubnetIPMask.getMask(lANIPv4Gateway);
	}

    public String getSubnetMask() {
        return subnetMask;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }
}