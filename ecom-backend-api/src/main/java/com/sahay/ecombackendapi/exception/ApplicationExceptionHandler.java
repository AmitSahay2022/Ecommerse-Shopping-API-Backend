package com.sahay.ecombackendapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ExceptionHandler(CartItemNotFoundException.class)
	public ResponseEntity<Map<String, Object>> cartItemNotFound(CartItemNotFoundException e) {
		Map<String, Object> errors = new HashMap<>();
		errors.put("message", e.getLocalizedMessage());
		errors.put("status code", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.NOT_FOUND);
	}
}
