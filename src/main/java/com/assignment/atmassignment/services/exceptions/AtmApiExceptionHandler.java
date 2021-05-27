package com.assignment.atmassignment.services.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.atmassignment.exceptions.AtmApiError;
import com.assignment.atmassignment.exceptions.AtmExistException;

@ControllerAdvice
public class AtmApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		AtmApiError apiError = new AtmApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
	  NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

	    AtmApiError apiError = new AtmApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
		AtmApiError apiError = new AtmApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
				"Constraint violation exception");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler(AtmExistException.class)
	protected ResponseEntity<Object> handleAtmExistException(Exception ex, WebRequest request) {
		AtmApiError apiError = new AtmApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
				"Duplicate Atm found, please add new");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		AtmApiError apiError = new AtmApiError(HttpStatus.SERVICE_UNAVAILABLE, ex.getLocalizedMessage(),
				"Service unavailable, please contact Admin with error details.");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}	

}
