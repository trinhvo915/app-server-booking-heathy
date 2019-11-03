package com.foody.dto;

import java.util.HashSet;
import java.util.Set;

import com.foody.entities.Clinic;
import com.foody.entities.Faculty;

public class ClinicResponse {
	
	private String id;
	
	private String name;
	
	private String address;
	
	private String latitude;
	
	private String longitude;
	
	private Set<Faculty> faculties = new HashSet<>();

	
	public ClinicResponse() {
		super();
	}

	public ClinicResponse(Clinic clinic) {
		this.name = clinic.getName();
		this.address = clinic.getAddress();
		this.latitude = clinic.getLatitude();
		this.longitude = clinic.getLongitude();
		this.faculties = clinic.getFaculties();
		this.id = clinic.getId();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
}
