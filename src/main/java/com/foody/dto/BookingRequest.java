package com.foody.dto;

import java.sql.Date;

public class BookingRequest {
	private String idDoctor;
	
	private String idClinic;
	
	private Date dateBooking;
	
	private String startTimeMorning;
	
	private String endTimeMorning;
	
	private String distanceMorning;

	private String startTimeAfternoon;
	
	private String endTimeAfternoon;
	
	private String distanceAfternoon;
	
	private String startTimeEverning;
	
	private String endTimeEverning;
	
	private String distanceEverning;
	
	public BookingRequest() {
		super();
	}
	
	public BookingRequest(String idDoctor, String idClinic, Date dateBooking, String startTimeMorning,
			String endTimeMorning, String distanceMorning, String startTimeAfternoon, String endTimeAfternoon,
			String distanceAfternoon, String startTimeEverning, String endTimeEverning, String distanceEverning) {
		super();
		this.idDoctor = idDoctor;
		this.idClinic = idClinic;
		this.dateBooking = dateBooking;
		this.startTimeMorning = startTimeMorning;
		this.endTimeMorning = endTimeMorning;
		this.distanceMorning = distanceMorning;
		this.startTimeAfternoon = startTimeAfternoon;
		this.endTimeAfternoon = endTimeAfternoon;
		this.distanceAfternoon = distanceAfternoon;
		this.startTimeEverning = startTimeEverning;
		this.endTimeEverning = endTimeEverning;
		this.distanceEverning = distanceEverning;
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public String getIdClinic() {
		return idClinic;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public void setIdClinic(String idClinic) {
		this.idClinic = idClinic;
	}

	public String getStartTimeAfternoon() {
		return startTimeAfternoon;
	}

	public String getEndTimeAfternoon() {
		return endTimeAfternoon;
	}

	public String getDistanceAfternoon() {
		return distanceAfternoon;
	}

	public String getStartTimeEverning() {
		return startTimeEverning;
	}

	public String getEndTimeEverning() {
		return endTimeEverning;
	}

	public String getDistanceEverning() {
		return distanceEverning;
	}

	public void setStartTimeAfternoon(String startTimeAfternoon) {
		this.startTimeAfternoon = startTimeAfternoon;
	}

	public void setEndTimeAfternoon(String endTimeAfternoon) {
		this.endTimeAfternoon = endTimeAfternoon;
	}

	public void setDistanceAfternoon(String distanceAfternoon) {
		this.distanceAfternoon = distanceAfternoon;
	}

	public void setStartTimeEverning(String startTimeEverning) {
		this.startTimeEverning = startTimeEverning;
	}

	public void setEndTimeEverning(String endTimeEverning) {
		this.endTimeEverning = endTimeEverning;
	}

	public void setDistanceEverning(String distanceEverning) {
		this.distanceEverning = distanceEverning;
	}

	public Date getDateBooking() {
		return dateBooking;
	}

	public String getStartTimeMorning() {
		return startTimeMorning;
	}

	public String getEndTimeMorning() {
		return endTimeMorning;
	}

	public String getDistanceMorning() {
		return distanceMorning;
	}

	public void setDateBooking(Date dateBooking) {
		this.dateBooking = dateBooking;
	}

	public void setStartTimeMorning(String startTimeMorning) {
		this.startTimeMorning = startTimeMorning;
	}

	public void setEndTimeMorning(String endTimeMorning) {
		this.endTimeMorning = endTimeMorning;
	}

	public void setDistanceMorning(String distanceMorning) {
		this.distanceMorning = distanceMorning;
	}

	@Override
	public String toString() {
		return "BookingRequest [idDoctor=" + idDoctor + ", idClinic=" + idClinic + ", dateBooking=" + dateBooking
				+ ", startTimeMorning=" + startTimeMorning + ", endTimeMorning=" + endTimeMorning + ", distanceMorning="
				+ distanceMorning + ", startTimeAfternoon=" + startTimeAfternoon + ", endTimeAfternoon="
				+ endTimeAfternoon + ", distanceAfternoon=" + distanceAfternoon + ", startTimeEverning="
				+ startTimeEverning + ", endTimeEverning=" + endTimeEverning + ", distanceEverning=" + distanceEverning
				+ "]";
	}
}
