package uk.co.virtual1.exception;

/**
 * @author Mikhail Tkachenko created on 01.06.16 18:03
 */
public class InternalApplicationException extends RuntimeException {

    public InternalApplicationException() {
        super("Internal error");
    }

    public InternalApplicationException(Throwable e) {
        super("Internal error:" + e.getMessage(), e);
    }

}
