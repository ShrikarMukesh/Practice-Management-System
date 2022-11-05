package com.citiustech.diagnosis.exception;

public class DiagnosisNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DiagnosisNotFoundException() {

	}
	public DiagnosisNotFoundException(String message) {
        super(message);
	}
}
