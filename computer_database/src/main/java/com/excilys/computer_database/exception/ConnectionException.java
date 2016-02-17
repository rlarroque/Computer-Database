package com.excilys.computer_database.exception;

/**
 * An exception that is thrown on critical errors about the database connection and closing.
 * @author rlarroque
 *
 */
public class ConnectionException extends RuntimeException {

	private static final long serialVersionUID = 42L;

	public ConnectionException(){
		super();
	}
	
	public ConnectionException(String message){
		super(message);
	}
	
	public ConnectionException(Throwable cause){
		super(cause);
	}
	
	public ConnectionException(String message, Throwable cause){
		super(message, cause);
	}
}
