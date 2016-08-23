package uk.co.virtual1.salesforce.object;

/**
 * @author Andrei Kilin created on 11.07.16.
 */

public class Virtual1DatacentrePostcode extends BaseSalesforceObject{

    private static final long serialVersionUID = 1L;
    public static final String RECORD_TYPE_VPN = "Datacentre Postcode";

    private String postcode; //Postcode__c
    private String exchangeName; //Virtual1_Exchange_Name__c


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

}
