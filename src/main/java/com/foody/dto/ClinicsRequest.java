package com.foody.dto;

public class ClinicsRequest {
	
	private String idClinic;
	
	private String idDoctor;

	public ClinicsRequest() {
		super();
	}

	public ClinicsRequest(String idClinic, String idDoctor) {
		super();
		this.idClinic = idClinic;
		this.idDoctor = idDoctor;
	}

	public String getIdClinic() {
		return idClinic;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdClinic(String idClinic) {
		this.idClinic = idClinic;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}
	
}
