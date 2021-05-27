package com.assignment.atmassignment.exceptions;

/**
 * @author Krishna
 *
 */
public class RestClientConsumerException extends Exception {

	private static final long serialVersionUID = 1L;

	public RestClientConsumerException() {
		super();
	}
	
	public RestClientConsumerException(String message) {
		super(message);
	}
	
	public RestClientConsumerException(Throwable throwable) {
		super(throwable);
	}
	
	public RestClientConsumerException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
}
