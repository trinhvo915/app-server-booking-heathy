package com.foody.payload;

public class ErrorData {
	
	private String field;
	
	private String message;

	public ErrorData() {

	}

	public ErrorData(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
