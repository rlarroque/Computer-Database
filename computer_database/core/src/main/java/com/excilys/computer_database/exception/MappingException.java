package com.excilys.computer_database.exception;

/**
 * Mapping exception.
 * @author rlarroque
 */
public class MappingException extends RuntimeException {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Default constructor.
     */
    public MappingException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message message
     */
    public MappingException(String message) {
        super(message);
        this.setMessage(message);
    }

    /**
     * Constructor with cause.
     * @param cause cause
     */
    public MappingException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with message and cause.
     * @param message message
     * @param cause causes
     */
    public MappingException(String message, Throwable cause) {
        super(message, cause);
        this.setMessage(message);
    }
}
