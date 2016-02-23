package com.excilys.computer_database.exception;

/**
 * Integrity exception used to validate backend integrity of passed values.
 * @author rlarroque
 *
 */
public class IntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public IntegrityException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message message
     */
    public IntegrityException(String message) {
        super(message);
    }

    /**
     * Constructor with cause.
     * @param cause cause
     */
    public IntegrityException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause.
     * @param message message
     * @param cause causes
     */
    public IntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

}
