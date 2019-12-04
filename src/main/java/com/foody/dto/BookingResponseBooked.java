package com.foody.dto;

import java.sql.Date;

import com.foody.entities.enums.UserGender;

public class BookingResponseBooked {
	
	private String id;
	
	private String namePatient;
	
	private String namePersonBooking;
	
	private Date dateBooking;
	
	private String numberPhone;
	
	private String pathology;
	
	private String address;
	
	private String birthdayYear;
	
	private String email;
	
	private UserGender gender;
	
	private Boolean isExit = false;
	
	private Boolean isActive = false;

	private String timeBooking;

	private UserResponse userBooked;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserResponse getUserBooked() {
		return userBooked;
	}

	public void setUserBooked(UserResponse userBooked) {
		this.userBooked = userBooked;
	}

	public String getNamePatient() {
		return namePatient;
	}

	public String getNamePersonBooking() {
		return namePersonBooking;
	}

	public Date getDateBooking() {
		return dateBooking;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public String getPathology() {
		return pathology;
	}

	public String getAddress() {
		return address;
	}

	public String getBirthdayYear() {
		return birthdayYear;
	}

	public String getEmail() {
		return email;
	}

	public UserGender getGender() {
		return gender;
	}

	public Boolean getIsExit() {
		return isExit;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public String getTimeBooking() {
		return timeBooking;
	}

	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
	}

	public void setNamePersonBooking(String namePersonBooking) {
		this.namePersonBooking = namePersonBooking;
	}

	public void setDateBooking(Date dateBooking) {
		this.dateBooking = dateBooking;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public void setPathology(String pathology) {
		this.pathology = pathology;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public void setIsExit(Boolean isExit) {
		this.isExit = isExit;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setTimeBooking(String timeBooking) {
		this.timeBooking = timeBooking;
	}
	
}
