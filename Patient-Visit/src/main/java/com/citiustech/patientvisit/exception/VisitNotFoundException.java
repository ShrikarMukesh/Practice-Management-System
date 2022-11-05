package com.citiustech.patientvisit.exception;

public class VisitNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public VisitNotFoundException() { }

	public VisitNotFoundException(String message) {
		super(message);
	}
}
