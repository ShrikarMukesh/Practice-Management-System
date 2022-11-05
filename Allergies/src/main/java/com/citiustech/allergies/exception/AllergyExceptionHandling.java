package com.citiustech.allergies.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllergyExceptionHandling {
      
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> allregyDoesNotExists(){
		return new ResponseEntity<>("Throws element is not found",HttpStatus.NOT_FOUND);
	}
}
