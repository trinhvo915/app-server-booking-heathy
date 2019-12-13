package com.foody.dto;

public class UserClinicView {
	
	private String idUser;
	
	private String idClinic;

	public UserClinicView(String idUser, String idClinic) {
		super();
		this.idUser = idUser;
		this.idClinic = idClinic;
	}

	public UserClinicView() {
		super();
	}

	public String getIdUser() {
		return idUser;
	}

	public String getIdClinic() {
		return idClinic;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setIdClinic(String idClinic) {
		this.idClinic = idClinic;
	}
	
}
