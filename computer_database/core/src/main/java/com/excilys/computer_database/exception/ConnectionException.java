package com.excilys.computer_database.exception;

/**
 * An exception that is thrown on critical errors about the database connection and closing.
 * @author rlarroque
 *
 */
public class ConnectionException extends RuntimeException {

    private static final long serialVersionUID = 42L;

    /**
     * Default constructor.
     */
    public ConnectionException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message message
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Constructor with cause.
     * @param cause cause
     */
    public ConnectionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause.
     * @param message message
     * @param cause causes
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
