package com.assignment.atmassignment.exceptions;

public class AtmExistException extends Exception{

	private static final long serialVersionUID = 1L;

	public AtmExistException() {
		super();
	}
	
	public AtmExistException(String message) {
		super(message);
	}
	
	public AtmExistException(Throwable throwable) {
		super(throwable);
	}
	
	public AtmExistException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
}
