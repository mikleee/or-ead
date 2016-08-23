package uk.co.virtual1.salesforce;

/**
 * @author Mikhail Tkachenko created on 23.08.16 14:22
 */
public class SalesforceException extends RuntimeException {
    public SalesforceException(String message) {
        super(message);
    }

    public SalesforceException(String message, Throwable e) {
        super(message, e);
    }
}
