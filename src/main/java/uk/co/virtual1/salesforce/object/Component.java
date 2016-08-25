package uk.co.virtual1.salesforce.object;

public class Component extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private String siteName;
    private String category;
    private String type;
    private String status;
    private String assetName;
    private String assetId;
    private String projectId;
    private Integer serviceTerms;


	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public Integer getServiceTerms() {
		return serviceTerms;
	}

	public void setServiceTerms(Integer serviceTerms) {
		this.serviceTerms = serviceTerms;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
