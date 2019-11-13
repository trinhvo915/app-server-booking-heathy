package com.foody.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.foody.entities.Booking;

public class DateBookingsFunction {

	public static List<String> getListDateBookings(List<Booking> bookingExperts) {
		List<String> dates = new ArrayList<String>();

		for (Booking booking : bookingExperts) {
			
			dates.add(booking.getDateBooking().toString());
		}
		List<String> datesAnother = new ArrayList<String>();
		
		for (String item : dates) {
			 if (!datesAnother.contains(item)) {
				 datesAnother.add(item);
	         }
		}
		
		if(datesAnother.size() != 0) {
			return datesAnother;
		}else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime today = LocalDateTime.now();
			String strDate = formatter.format(today);
			datesAnother.add(strDate);
			return datesAnother;
		}
	}
}
