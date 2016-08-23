package uk.co.virtual1.salesforce.object;

public class CloudProvisioning extends BaseSalesforceObject { //cloud_provisioning__c

    private Double maximumCPU; // Demo_Maximum_CPU__c
    private Double maximumRAM; // Demo_Maximum_RAM__c
    private Double maximumDisk; // Demo_Maximum_Disk__c
    private Double maximumIPAddress; // Demo_Maximum_IP_Address__c

    public Double getMaximumCPU() {
        return maximumCPU;
    }

    public void setMaximumCPU(Double maximumCPU) {
        this.maximumCPU = maximumCPU;
    }

    public Double getMaximumRAM() {
        return maximumRAM;
    }

    public void setMaximumRAM(Double maximumRAM) {
        this.maximumRAM = maximumRAM;
    }

    public Double getMaximumDisk() {
        return maximumDisk;
    }

    public void setMaximumDisk(Double maximumDisk) {
        this.maximumDisk = maximumDisk;
    }

    public Double getMaximumIPAddress() {
        return maximumIPAddress;
    }

    public void setMaximumIPAddress(Double maximumIPAddress) {
        this.maximumIPAddress = maximumIPAddress;
    }
}
