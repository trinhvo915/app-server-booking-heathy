package com.foody.payload;

public class Data {
	private String message;
	
	private Integer status;
	
	private Object object;
	
	public Data(String message, Integer status) {
		this.message = message;
		this.status = status;
	}


	public Data(String message, Integer status, Object object) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
