package com.foody.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.foody.entities.enums.UserGender;

@Entity
@Table(name ="booking")
public class Booking extends AuditEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
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
	
	private String timeBooking;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_expert")
    private User expert;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;
	
	public Booking() {
		
	}

	public Booking(Date dateBooking) {
		super();
		this.dateBooking = dateBooking;
	}

	public Booking(Date dateBooking, String timeBooking) {
		this.dateBooking = dateBooking;
		this.timeBooking = timeBooking;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public String getNamePersonBooking() {
		return namePersonBooking;
	}

	public void setNamePersonBooking(String namePersonBooking) {
		this.namePersonBooking = namePersonBooking;
	}

	public User getUser() {
		return user;
	}

	public void setExpert(User expert) {
		this.expert = expert;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTimeBooking() {
		return timeBooking;
	}

	public void setTimeBooking(String timeBooking) {
		this.timeBooking = timeBooking;
	}

	public Date getDateBooking() {
		return dateBooking;
	}

	public void setDateBooking(Date dateBooking) {
		this.dateBooking = dateBooking;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public Boolean getIsExit() {
		return isExit;
	}

	public void setIsExit(Boolean isExit) {
		this.isExit = isExit;
	}

	public String getPathology() {
		return pathology;
	}

	public void setPathology(String pathology) {
		this.pathology = pathology;
	}

	public String getNamePatient() {
		return namePatient;
	}

	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
	}

	public String getBirthdayYear() {
		return birthdayYear;
	}

	public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}
		
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(this.getId(), booking.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
