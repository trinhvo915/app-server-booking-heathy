package com.foody.controller;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foody.dto.BookingRequest;
import com.foody.payload.ApiResponse;
import com.foody.payload.DataResponse;
import com.foody.services.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
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
}
