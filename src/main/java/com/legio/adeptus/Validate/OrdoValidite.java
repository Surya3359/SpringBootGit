package com.legio.adeptus.Validate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrdoValidite extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldname, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String error = "Invalid DateFormat. Please use 'yyyy-MM-dd' to enter date.";
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	protected ResponseEntity<String> handleDuplicateEntries(DataIntegrityViolationException dx){
		String error = dx.getMostSpecificCause().getMessage();
		String split[]=error.split("for");
		String message = split[0];
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
}
