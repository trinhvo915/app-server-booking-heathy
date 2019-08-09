package com.foody.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.foody.payload.ErrorData;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", new Date());
	        body.put("status", status.value());

	        //Get all errors
	        List<ErrorData> errors = new ArrayList<ErrorData>(); 
	        List<FieldError> errorArguments = ex.getBindingResult().getFieldErrors();
	        for (FieldError fieldError : errorArguments) {
	        	errors.add(new ErrorData(fieldError.getField(),fieldError.getDefaultMessage()));
			}

	        body.put("errors", errors);

	        return new ResponseEntity<>(body, headers, status);
	}	
}
