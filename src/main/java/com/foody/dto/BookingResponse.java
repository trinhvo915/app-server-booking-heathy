package com.foody.dto;

import java.sql.Date;

public class BookingResponse {
	
	private String id;
	
	private Date dateBooking;
	
	private String timeBooking;
	
	private Boolean isExit;

	public BookingResponse() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateBooking() {
		return dateBooking;
	}

	public String getTimeBooking() {
		return timeBooking;
	}

	public Boolean getIsExit() {
		return isExit;
	}

	public void setDateBooking(Date dateBooking) {
		this.dateBooking = dateBooking;
	}

	public void setTimeBooking(String timeBooking) {
		this.timeBooking = timeBooking;
	}

	public void setIsExit(Boolean isExit) {
		this.isExit = isExit;
	}
}
