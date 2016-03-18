package com.excilys.computer_database.exception;

/**
 * @author rlarroque
 */
public class DateException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     */
    public DateException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message message
     */
    public DateException(String message) {
        super(message);
    }

    /**
     * Constructor with cause.
     * @param cause cause
     */
    public DateException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause.
     * @param message message
     * @param cause causes
     */
    public DateException(String message, Throwable cause) {
        super(message, cause);
    }
}
