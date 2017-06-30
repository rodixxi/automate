package com.harriague.automate.core.exceptions;

public class ExecutionException extends Exception {

	/**
	 * Serial Id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception
	 * @param message exception message
	 */
	public ExecutionException(String message) {
		super(message);
	}
	
	/**
	 * Create an exception
	 * @param cause exception cause
	 */
	public ExecutionException(Exception cause) {
		super(cause);
	}
	
	/**
	 * Create an exception
	 * @param message exception message
	 * @param cause exception cause
	 */
	public ExecutionException(String message, Throwable cause) {
		super(message, cause);
	}
}
