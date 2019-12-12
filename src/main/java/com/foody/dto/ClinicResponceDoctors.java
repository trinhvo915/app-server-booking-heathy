package com.foody.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.foody.entities.Attachment;
import com.foody.entities.Faculty;
import com.foody.entities.Price;

public class ClinicResponceDoctors {
	
	private String id;
	
	private String name;
	
	private String address;
	
	private String latitude;
	
	private String longitude;
	
	private Date createAt;
	
	private String createdBy;
	
	private Set<Faculty> faculties = new HashSet<>();
	
	private Set<Price> prices = new HashSet<>();
	
	private Set<Attachment> photoClinics = new HashSet<>();
	
	private Attachment photoClinicLogo  ;
	
	private List<UserResponceClinic> userResponceClinics = new ArrayList<UserResponceClinic>();
	
	public ClinicResponceDoctors() {
		
	}

	public ClinicResponceDoctors(String id, String name, String address, String latitude, String longitude,
			Date createAt, String createdBy, Set<Faculty> faculties, Set<Price> prices, Set<Attachment> photoClinics,
			List<UserResponceClinic> userResponceClinics) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.createAt = createAt;
		this.createdBy = createdBy;
		this.faculties = faculties;
		this.prices = prices;
		this.photoClinics = photoClinics;
		this.userResponceClinics = userResponceClinics;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Attachment getPhotoClinicLogo() {
		return photoClinicLogo;
	}

	public void setPhotoClinicLogo(Attachment photoClinicLogo) {
		this.photoClinicLogo = photoClinicLogo;
	}

	public String getAddress() {
		return address;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Set<Faculty> getFaculties() {
		return faculties;
	}

	public Set<Price> getPrices() {
		return prices;
	}

	public Set<Attachment> getPhotoClinics() {
		return photoClinics;
	}

	public List<UserResponceClinic> getUserResponceClinics() {
		return userResponceClinics;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

	public void setPrices(Set<Price> prices) {
		this.prices = prices;
	}

	public void setPhotoClinics(Set<Attachment> photoClinics) {
		this.photoClinics = photoClinics;
	}

	public void setUserResponceClinics(List<UserResponceClinic> userResponceClinics) {
		this.userResponceClinics = userResponceClinics;
	}
}
