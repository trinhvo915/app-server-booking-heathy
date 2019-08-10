package com.foody.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foody.payload.Data;
import com.foody.payload.ErrorData;

//https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler implements AuthenticationFailureHandler{
	
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
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		 Map<String, Object> bodys = new LinkedHashMap<>();
		 Data erros = new Data("Parameter is missing", HttpStatus.INTERNAL_SERVER_ERROR.value());
		 bodys.put("success", false);
		 bodys.put("errors", erros);
		 return new ResponseEntity<Object>(bodys, headers, status);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Data erros = new Data(ex.getParameterName() + " parameter is missing", HttpStatus.BAD_REQUEST.value());
		 return new ResponseEntity<Object>(erros, headers, status);
	}

	// custom handle 401 error with :  implements AuthenticationFailureHandler
	private ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		Map<String, Object> data = new HashMap<>();
		
		data.put("timestamp",Calendar.getInstance().getTime());
		data.put("errors",exception.getMessage());
		
		response.getOutputStream().println(objectMapper.writeValueAsString(data));
	}
}
