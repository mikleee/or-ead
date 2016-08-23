package uk.co.virtual1.exception;

/**
 * Exception is thrown when xml marshaling unmarshalling is failed
 *
 * @author Mikhail Tkachenko created on 08.04.16 13:50
 */
public final class SerializationException extends InternalApplicationException {

    public SerializationException(Exception e) {
        super("Cant serialize message. Reason:  " + e.getMessage());
    }

}
