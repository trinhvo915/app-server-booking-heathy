package com.foody.services;

import com.foody.dto.BookingRequest;
import com.foody.dto.BookingRequestUpdate;
import com.foody.payload.DataResponse;
import com.foody.security.UserPrincipal;

public interface BookingService {
	DataResponse createBooking(BookingRequest bookingRequest);
	
	DataResponse updateBooking(BookingRequestUpdate bookingRequestUpdate, UserPrincipal currentUser);
	
	DataResponse getBookedBooking(UserPrincipal currentUser,String idClinic);
	
	DataResponse getBookedBooking(UserPrincipal currentUser);
	
	DataResponse deleteBooking(String id);
}
