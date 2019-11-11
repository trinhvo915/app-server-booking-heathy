package com.foody.payload;

public class DataResponse {
	
	private Boolean success;
	
	private Data data;

	public DataResponse() {
		super();
	}

	public DataResponse(Boolean success,Data data) {
		this.success = success;
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
