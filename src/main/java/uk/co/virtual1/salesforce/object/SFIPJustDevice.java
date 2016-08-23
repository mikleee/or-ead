package uk.co.virtual1.salesforce.object;


public class SFIPJustDevice extends BaseSalesforceObject {

    private String equipmentName; //EquipmentName__c , Text 100, Mandatory
    private String manufacturerName; //ManufacturerName__c , Text 100
    private String modelNumber; //ModelNumber__c , Text 100
    private String otherData; //OtherData__c , Text 500, Mandatory
    private String dateRequested; //DateRequested__c , Text 20
    private Long currentNumber;
    private String ipJustId;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }

    public Long getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Long currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getIpJustId() {
        return ipJustId;
    }

    public void setIpJustId(String ipJustId) {
        this.ipJustId = ipJustId;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        if (equipmentName != null) builder.append("equipmentName=").append(equipmentName);
        return builder.toString();
    }
}
