package com.employeeapp.exception;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException extends RuntimeException {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException e) {
		String message = e.getMessage();
		ApiResponse response = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Set<String>> constraintViolationException(ConstraintViolationException cve) {
		Set<String> set = new HashSet<String>();
		cve.getConstraintViolations().forEach(constraint -> {
			String message = constraint.getMessage();
			set.add(message);
		});
		return new ResponseEntity<Set<String>>(set, HttpStatus.BAD_REQUEST);

	}
}
