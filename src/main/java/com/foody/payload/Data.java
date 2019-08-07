package com.foody.payload;

public class Data {
	private String message;
	
	private String status;
	
	private Object object;
	
	public Data(String message, String status) {
		this.message = message;
		this.status = status;
	}


	public Data(String message, String status, Object object) {
		this.message = message;
		this.status = status;
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
