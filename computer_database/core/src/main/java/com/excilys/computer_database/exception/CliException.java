package com.excilys.computer_database.exception;

/**
 * @author rlarroque
 */
public class CliException extends RuntimeException {

    /**
     * Default constructor.
     */
    public CliException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message message
     */
    public CliException(String message) {
        super(message);
    }

    /**
     * Constructor with cause.
     * @param cause cause
     */
    public CliException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause.
     * @param message message
     * @param cause causes
     */
    public CliException(String message, Throwable cause) {
        super(message, cause);
    }
}

