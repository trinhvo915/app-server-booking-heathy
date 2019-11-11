package com.foody.utils;

import java.util.ArrayList;
import java.util.List;
import com.foody.dto.BookingResponse;
import com.foody.entities.Booking;

public class BookingFunction {
	public static List<BookingResponse> getBookingDoctor(List<Booking> bookingExperts) {
		List<BookingResponse> bookingResponses = new ArrayList<BookingResponse>();
		
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
