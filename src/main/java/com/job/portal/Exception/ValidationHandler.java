package com.job.portal.Exception;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ValidationHandler extends ResponseEntityExceptionHandler{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> response=new HashMap<>();
		
		List<ObjectError> err= ex.getBindingResult().getAllErrors();
		
		
		err.forEach(
				(error) ->{
					String fieldName=((FieldError)error).getField();
					String message=error.getDefaultMessage();
					
					response.put(fieldName, message);
				}
				
				);
		
		
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}

	
	

}
