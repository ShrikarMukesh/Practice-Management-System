package com.citiustech.allergies.exception;

@SuppressWarnings("serial")
public class AllergyNotFoundException extends RuntimeException {
	
	
	public AllergyNotFoundException(String message){
		super(message);
	}
	
}
