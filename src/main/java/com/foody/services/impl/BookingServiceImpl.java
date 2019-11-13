package com.foody.services.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.BookingRequest;
import com.foody.entities.Booking;
import com.foody.entities.Clinic;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.BookingRepository;
import com.foody.repository.ClinicRepository;
import com.foody.repository.UserRepository;
import com.foody.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public DataResponse createBooking(BookingRequest bookingRequest) {
		Clinic clinic = clinicRepository.findByIdClinicAndIdUser(bookingRequest.getIdClinic(), bookingRequest.getIdDoctor());
		List<Booking> bookings = new ArrayList<Booking>();
		if(clinic !=null) {
			if(!"".equals(bookingRequest.getStartTimeMorning()) && !"".equals(bookingRequest.getEndTimeMorning())) {
				LocalTime timeStart = LocalTime.parse(bookingRequest.getStartTimeMorning(),
			            DateTimeFormatter.ofPattern("KK:mm a"));
				LocalTime timeEnd = LocalTime.parse(bookingRequest.getEndTimeMorning(),
			            DateTimeFormatter.ofPattern("KK:mm a"));
				 
				if (timeEnd.isAfter(timeStart)) {
					 	Booking bookingStart = new Booking(bookingRequest.getDateBooking(),timeStart.toString());
					 	User userStart = userRepository.getOne(bookingRequest.getIdDoctor());
					 	bookingStart.setExpert(userStart);
					 	Clinic clinicStart = clinicRepository.getOne(bookingRequest.getIdClinic());
					 	bookingStart.setClinic(clinicStart);
					 	
						bookings.add(bookingStart);
						
						boolean flag = true;
						while (flag) {
							timeStart =  timeStart.plusMinutes(Integer.parseInt(bookingRequest.getDistanceMorning()));
							
							Booking booking = new Booking(bookingRequest.getDateBooking(),timeStart.toString());
							booking.setExpert(userStart);
							booking.setClinic(clinicStart);
							bookings.add(booking);
							if(timeStart.compareTo(timeEnd)==0) {
								flag = false;
							}
	
						}
			      }
				
			}
			
			if(!"".equals(bookingRequest.getStartTimeAfternoon()) && !"".equals(bookingRequest.getEndTimeAfternoon())) {
				LocalTime timeStart = LocalTime.parse(bookingRequest.getStartTimeAfternoon(),
			            DateTimeFormatter.ofPattern("HH:mm a"));
				LocalTime timeEnd = LocalTime.parse(bookingRequest.getEndTimeAfternoon(),
			            DateTimeFormatter.ofPattern("HH:mm a"));
				 
				if (timeEnd.isAfter(timeStart)) {
					 	Booking bookingStart = new Booking(bookingRequest.getDateBooking(),timeStart.toString());
					 	
					 	User userStart = userRepository.getOne(bookingRequest.getIdDoctor());
					 	bookingStart.setExpert(userStart);
					 	Clinic clinicStart = clinicRepository.getOne(bookingRequest.getIdClinic());
					 	bookingStart.setClinic(clinicStart);
					 	
						bookings.add(bookingStart);
						
						boolean flag = true;
						while (flag) {
							timeStart =  timeStart.plusMinutes(Integer.parseInt(bookingRequest.getDistanceAfternoon()));
							Booking booking = new Booking(bookingRequest.getDateBooking(),timeStart.toString());
							booking.setExpert(userStart);
							booking.setClinic(clinicStart);
							bookings.add(booking);
							if(timeStart.compareTo(timeEnd)==0) {
								flag = false;
							}

						}
			      }
				
			}
			
			if(!"".equals(bookingRequest.getStartTimeEverning()) && !"".equals(bookingRequest.getEndTimeEverning())) {
				LocalTime timeStart = LocalTime.parse(bookingRequest.getStartTimeEverning(),
			            DateTimeFormatter.ofPattern("HH:mm a"));
				LocalTime timeEnd = LocalTime.parse(bookingRequest.getEndTimeEverning(),
			            DateTimeFormatter.ofPattern("HH:mm a"));
				 
				if (timeEnd.isAfter(timeStart)) {
					 	Booking bookingStart = new Booking(bookingRequest.getDateBooking(),timeStart.toString());
					 	User userStart = userRepository.getOne(bookingRequest.getIdDoctor());
					 	bookingStart.setExpert(userStart);
					 	Clinic clinicStart = clinicRepository.getOne(bookingRequest.getIdClinic());
					 	bookingStart.setClinic(clinicStart);
					 	
						bookings.add(bookingStart);
						
						boolean flag = true;
						while (flag) {
							timeStart =  timeStart.plusMinutes(Integer.parseInt(bookingRequest.getDistanceEverning()));
							
							Booking booking = new Booking(bookingRequest.getDateBooking(),timeStart.toString());
							booking.setExpert(userStart);
							booking.setClinic(clinicStart);
							
							bookings.add(booking);
							if(timeStart.compareTo(timeEnd)==0) {
								flag = false;
							}

						}
			      }
				
			}
			
			
			List<Booking> dataBookings = bookingRepository.saveAll(bookings);
			
			if(dataBookings.size() ==0) {
				 return new DataResponse(false, new Data("Tạo Lịch không thành công !",HttpStatus.BAD_REQUEST.value()));
			}
			return new DataResponse(true, new Data("Tạo Lịch thành công !",HttpStatus.OK.value(),dataBookings));
		}
		return new DataResponse(true, new Data("Bác sỹ hoặc phòng khám không tồn tại !",HttpStatus.BAD_REQUEST.value()));
		
	}

}
