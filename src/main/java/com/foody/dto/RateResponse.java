package com.foody.dto;

import com.foody.entities.enums.NumberStar;

public class RateResponse {
	
	private NumberStar numberStar;
	
	private Double value;
	
	private String idUser;
	
	private String idDoctor;

	public RateResponse() {
		
	}

	public RateResponse(NumberStar numberStar, Double value, String idUser, String idDoctor) {
		this.numberStar = numberStar;
		this.value = value;
		this.idUser = idUser;
		this.idDoctor = idDoctor;
	}

	public NumberStar getNumberStar() {
		return numberStar;
	}

	public Double getValue() {
		return value;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setNumberStar(NumberStar numberStar) {
		this.numberStar = numberStar;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}
}
