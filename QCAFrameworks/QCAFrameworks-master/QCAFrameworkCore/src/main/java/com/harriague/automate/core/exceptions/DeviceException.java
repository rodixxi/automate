package com.harriague.automate.core.exceptions;

public class DeviceException extends Exception {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create an exception
	 * @param message exception message
	 */
	public DeviceException(String message) {
		super(message);
	}
	
	/**
	 * Create an exception
	 * @param cause exception cause
	 */
	public DeviceException(Exception cause) {
		super(cause);
	}
	
	/**
	 * Create an exception
	 * @param message exception message
	 * @param cause exception cause
	 */
	public DeviceException(String message, Throwable cause) {
		super(message, cause);
	}
}
