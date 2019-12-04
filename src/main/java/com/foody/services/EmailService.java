package com.foody.services;

import javax.servlet.http.HttpServletRequest;

import com.foody.dto.BookingRequestUpdate;
import com.foody.entities.User;

public interface EmailService {
	
	void sendEmail(String email, HttpServletRequest httpRequest, User user);
	
	void sendEmailBookingClient(BookingRequestUpdate bookingUpdate);
	
	void sendEmailBookingDoctor(BookingRequestUpdate bookingUpdate);
	
	void sendEmailBookingBussy(String idDoctor, String idBooked);
}
