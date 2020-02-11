package com.foody.controller;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foody.dto.BookingRequest;
import com.foody.dto.BookingRequestUpdate;
import com.foody.payload.ApiResponse;
import com.foody.payload.DataResponse;
import com.foody.security.CurrentUser;
import com.foody.security.UserPrincipal;
import com.foody.services.BookingService;
import com.foody.services.impl.EmailServiceImpl;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest bookingRequest){
		DataResponse data = bookingService.createBooking(bookingRequest);
		if(data.getSuccess() == false) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, data.getData().getMessage()));
		}
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/create/booking")
                .buildAndExpand(data.getData().getObject()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, data.getData().getMessage()));
	}
	
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBooking(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody BookingRequestUpdate bookingRequestUpdate){
		DataResponse data = bookingService.updateBooking(bookingRequestUpdate, currentUser);
		if(data.getSuccess() == false) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, data.getData().getMessage()));
		}
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/create/booking")
                .buildAndExpand(data.getData().getObject()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, data.getData().getMessage()));
	}
	
	@RequestMapping(value="/send-email", method = RequestMethod.POST)
	public ResponseEntity<?> sendMailBooking(@CurrentUser UserPrincipal currentUser,@Valid @RequestBody BookingRequestUpdate bookingRequestUpdate){
		emailService.sendEmailBookingClient(bookingRequestUpdate);
		
		emailService.sendEmailBookingDoctor(bookingRequestUpdate);
		return ResponseEntity.ok().body(new ApiResponse(true, "send mail success !!"));
	}
	
	@RequestMapping(value="/bookeds/{id_clinic}", method = RequestMethod.GET)
	public DataResponse getListBookeds(@CurrentUser UserPrincipal currentUser,@PathVariable("id_clinic") String id_clinic){
		return bookingService.getBookedBooking(currentUser, id_clinic);
	}
	
	@RequestMapping(value="/bookeds/user", method = RequestMethod.GET)
	public DataResponse getListBookedsForUser(@CurrentUser UserPrincipal currentUser){
		return bookingService.getBookedBooking(currentUser);
	}
	
	@RequestMapping(value="/send-email/busyy/{id_booked}", method = RequestMethod.GET)
	public ResponseEntity<?> sendMailBusyy(@CurrentUser UserPrincipal currentUser,@PathVariable("id_booked") String id_booked ){
		emailService.sendEmailBookingBussy(currentUser.getId(), id_booked);;
		return ResponseEntity.ok().body(new ApiResponse(true, "Đã gửi mail !!"));
	}
	
	@RequestMapping(value="/delete/{id_booking}", method = RequestMethod.POST)
	public ResponseEntity<?> deleteBookeds(@PathVariable("id_booking") String id_booking){
		DataResponse data = bookingService.deleteBooking(id_booking);
		
		if(data.getSuccess() == false) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, data.getData().getMessage()));
		}
		
		URI location = ServletUriComponentsBuilder
	                .fromCurrentContextPath().path("/users")
	                .buildAndExpand(data.getData().getObject()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, data.getData().getMessage()));
	}
}
