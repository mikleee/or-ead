package uk.co.virtual1.salesforce.object;

public class VPNItem extends BaseSalesforceObject {

    private static final long serialVersionUID = 1L;

	private String CIR;

	//for VPN details page, not populated 
    private String accessName;
    private String siteName;
    private String accessType;
    private String relation;
    private String accessStatus;
    private String endCustomerId;
    private String endCustomerName;
    
	public String getCIR() {
		return CIR;
	}
	public void setCIR(String cIR) {
		CIR = cIR;
	}
	public String getAccessName() {
		return accessName;
	}
	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getAccessStatus() {
		return accessStatus;
	}
	public void setAccessStatus(String accessStatus) {
		this.accessStatus = accessStatus;
	}
	public String getEndCustomerId() {
		return endCustomerId;
	}
	public void setEndCustomerId(String endCustomerId) {
		this.endCustomerId = endCustomerId;
	}
	public String getEndCustomerName() {
		return endCustomerName;
	}
	public void setEndCustomerName(String endCustomerName) {
		this.endCustomerName = endCustomerName;
	}
	
	
    
}
