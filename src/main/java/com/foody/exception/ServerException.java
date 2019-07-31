package com.foody.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ServerException(String message) {
		super(message);
	}
	
	public ServerException(String message, Throwable cause) {
		super(message,cause);
	}
}
