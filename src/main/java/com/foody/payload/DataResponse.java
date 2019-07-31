package com.foody.payload;

public class DataResponse {
	
	private Boolean success;
	
	private String message;

	public DataResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
