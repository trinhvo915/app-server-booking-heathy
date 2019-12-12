package com.foody.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.foody.entities.Faculty;

public class ClinicRequest {
	
	@NotNull(message = "Hãy Nhập nhập tên phòng khám !")
	private String name;
	
	@NotNull(message = "Hãy Nhập nhập địa chỉ phòng khám !")
	private String address;
	
	private String latitude;
	
	private String longitude;

	private String addressQuery ;
	
	private Set<Faculty> faculties = new HashSet<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressQuery() {
		return addressQuery;
	}

	public void setAddressQuery(String addressQuery) {
		this.addressQuery = addressQuery;
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

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}
	
}
