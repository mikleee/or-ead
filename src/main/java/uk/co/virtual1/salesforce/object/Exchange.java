package uk.co.virtual1.salesforce.object;

/**
 * @author Mikhail Tkachenko created on 18.08.16 13:28
 */
public class Exchange extends BaseSalesforceObject {

    private String exchangeName;


    public Exchange() {
    }

    public Exchange(String name) {
        this.name = name;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }
}
