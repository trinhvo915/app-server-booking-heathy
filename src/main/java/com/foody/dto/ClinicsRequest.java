package com.foody.dto;

import java.util.Date;

public class ClinicsRequest {
	
	private String idClinic;
	
	private String idDoctor;

	public ClinicsRequest() {
		super();
	}
	
	private Date dateQurey;
	
	private Date dateCurrent;
	
	public ClinicsRequest(String idClinic, String idDoctor) {
		super();
		this.idClinic = idClinic;
		this.idDoctor = idDoctor;
	}

	public ClinicsRequest(String idClinic, String idDoctor, Date dateQurey) {
		super();
		this.idClinic = idClinic;
		this.idDoctor = idDoctor;
		this.dateQurey = dateQurey;
	}


	public ClinicsRequest(String idClinic, String idDoctor, Date dateQurey, Date dateCurrent) {
		super();
		this.idClinic = idClinic;
		this.idDoctor = idDoctor;
		this.dateQurey = dateQurey;
		this.dateCurrent = dateCurrent;
	}

	public Date getDateCurrent() {
		return dateCurrent;
	}

	public void setDateCurrent(Date dateCurrent) {
		this.dateCurrent = dateCurrent;
	}

	public Date getDateQurey() {
		return dateQurey;
	}

	public void setDateQurey(Date dateQurey) {
		this.dateQurey = dateQurey;
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
