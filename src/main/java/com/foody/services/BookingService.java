package com.foody.services;

import com.foody.dto.BookingRequest;
import com.foody.payload.DataResponse;

public interface BookingService {
	DataResponse createBooking(BookingRequest bookingRequest);
	
}
