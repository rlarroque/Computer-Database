package com.excilys.computer_database.exception;

/**
 * Integrity exception used to validate backend integrity of passed values.
 * @author rlarroque
 *
 */
public class IntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
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
    public IntegrityException() {
        super();
    }

    /**
     * Constructor with message.
     * @param message message
     */
    public IntegrityException(String message) {
        super(message);
        this.setMessage(message);
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
        this.setMessage(message);
    }
}
