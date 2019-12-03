package com.foody.dto;

public class PriceRequest {
	
	private String totalPrice;
	
	private String description;

	public PriceRequest() {
		
	}

	public PriceRequest(String totalPrice, String description) {
		this.totalPrice = totalPrice;
		this.description = description;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
