package uk.co.virtual1.salesforce.object;

public class NNI extends BaseSalesforceObject {

    private static final long serialVersionUID = 1L;

    private String carrierNniId;
    private String nniDescription;
    private String upstreamDeviceName;
    private String status;
    private String carrierProvider;
    private String carrierProviderId;
    private String nniType;

    public String getCarrierNniId() {
        return carrierNniId;
    }

    public void setCarrierNniId(String carrierNniId) {
        this.carrierNniId = carrierNniId;
    }

    public String getNniDescription() {
        return nniDescription;
    }

    public void setNniDescription(String nniDescription) {
        this.nniDescription = nniDescription;
    }

    public String getUpstreamDeviceName() {
        return upstreamDeviceName;
    }

    public void setUpstreamDeviceName(String upstreamDeviceName) {
        this.upstreamDeviceName = upstreamDeviceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarrierProvider() {
        return carrierProvider;
    }

    public void setCarrierProvider(String carrierProvider) {
        this.carrierProvider = carrierProvider;
    }

    public String getNniType() {
        return nniType;
    }

    public void setNniType(String nniType) {
        this.nniType = nniType;
    }

    public String getCarrierProviderId() {
        return carrierProviderId;
    }

    public void setCarrierProviderId(String carrierProviderId) {
        this.carrierProviderId = carrierProviderId;
    }
}
