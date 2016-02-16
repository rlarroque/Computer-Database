package com.excilys.computer_database.exception;

/**
 * Integrity exception used to validate backend integrity of passed values.
 * @author excilys
 *
 */
public class IntegrityException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IntegrityException(){
		super();
	}
	
	public IntegrityException(String message){
		super(message);
	}
	
	public IntegrityException(Throwable cause){
		super(cause);
	}
	
	public IntegrityException(String message, Throwable cause){
		super(message, cause);
	}

}
