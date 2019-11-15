package com.foody.dto;
import com.foody.entities.enums.UserGender;

public class BookingRequestUpdate {
	
	private String idBooking;
	
	private String idDoctor;
	
	private String nameClinc ;
	
	private String namePatient;
	
	private String namePersinBooking;
	
	private String nameDoctor;
	
	private String numberPhone;
	
	private String pathology;
	
	private String address;
	
	private String dateBooking;
	
	private String birthdayYear;
	
	private String timeBooking;
	
	private String email;
	
	private String addressClinic;
	
	private UserGender gender;
	
	public BookingRequestUpdate() {
		super();
	}

	public String getNamePersinBooking() {
		return namePersinBooking;
	}

	public void setNamePersinBooking(String namePersinBooking) {
		this.namePersinBooking = namePersinBooking;
	}

	public String getAddressClinic() {
		return addressClinic;
	}

	public void setAddressClinic(String addressClinic) {
		this.addressClinic = addressClinic;
	}

	public String getTimeBooking() {
		return timeBooking;
	}

	public void setTimeBooking(String timeBooking) {
		this.timeBooking = timeBooking;
	}

	public String getDateBooking() {
		return dateBooking;
	}

	public void setDateBooking(String dateBooking) {
		this.dateBooking = dateBooking;
	}

	public String getNameDoctor() {
		return nameDoctor;
	}

	public void setNameDoctor(String nameDoctor) {
		this.nameDoctor = nameDoctor;
	}

	public String getNameClinc() {
		return nameClinc;
	}

	public void setNameClinc(String nameClinc) {
		this.nameClinc = nameClinc;
	}

	public String getIdBooking() {
		return idBooking;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdBooking(String idBooking) {
		this.idBooking = idBooking;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getNamePatient() {
		return namePatient;
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

	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
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
	
}
