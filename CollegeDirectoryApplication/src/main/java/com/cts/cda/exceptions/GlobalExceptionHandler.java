package com.cts.cda.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String,String> errormap= new HashMap<>();
		for (FieldError error: ex.getBindingResult().getFieldErrors()) {
			errormap.put(error.getField(),error.getDefaultMessage());
		}
		return errormap;
	}
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) 
	{ 
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
