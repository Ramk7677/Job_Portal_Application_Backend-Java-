package com.job.portal.Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalException {

	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<?> userNotFoundExceptionhandler(UserIdNotFoundException ex, WebRequest request)
	{
		Map<String, String> response=new HashMap<String, String>();
		
		response.put("date", new Date().toString());
		response.put("message", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> Exceptionhandler(Exception ex, WebRequest request)
	{
		Map<String, String> response=new HashMap<String, String>();
		
		response.put("date", new Date().toString());
		response.put("message", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
