package com.foody.utils;

import java.util.HashSet;
import java.util.Set;
import com.foody.dto.BookingResponse;
import com.foody.entities.Booking;

public class BookingFunction {
	public static Set<BookingResponse> getBookingDoctor(Set<Booking> bookingExperts) {
		Set<BookingResponse> bookingResponses = new HashSet<BookingResponse>();
		
		for (Booking item : bookingExperts) {
			BookingResponse response = new BookingResponse();
			response.setId(item.getId());
			response.setDateBooking(item.getDateBooking());
			response.setTimeBooking(item.getTimeBooking());
			response.setIsExit(item.getIsExit());
			
			bookingResponses.add(response);
		}
		return bookingResponses;
	}
}
