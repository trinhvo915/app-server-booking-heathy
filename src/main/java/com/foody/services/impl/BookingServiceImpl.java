package com.foody.services.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.foody.dto.BookingRequest;
import com.foody.dto.BookingRequestUpdate;
import com.foody.dto.BookingResponseBooked;
import com.foody.dto.ClinicResponse;
import com.foody.dto.DoctorResponse;
import com.foody.dto.UserResponse;
import com.foody.entities.Attachment;
import com.foody.entities.Booking;
import com.foody.entities.Clinic;
import com.foody.entities.Comment;
import com.foody.entities.Rate;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.BookingRepository;
import com.foody.repository.ClinicRepository;
import com.foody.repository.CommentRepositiry;
import com.foody.repository.RateRepository;
import com.foody.repository.UserRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.BookingService;
import com.foody.utils.AttacchmetFunction;
import com.foody.utils.RateFunction;

@Service
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepositiry commentRepositiry;
	
	@Autowired
	RateRepository rateRepository;
	
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
		return new DataResponse(false, new Data("Bác sỹ hoặc phòng khám không tồn tại !",HttpStatus.BAD_REQUEST.value()));
		
	}

	@Override
	public DataResponse updateBooking(BookingRequestUpdate bookingUpdate, UserPrincipal currentUser) {
		Booking booking = bookingRepository.checkBookingWithIdBookingAnIdDoctor(bookingUpdate.getIdBooking(), bookingUpdate.getIdDoctor(), false);
		
		if(booking != null) {
			User user = userRepository.getOne(currentUser.getId());
			booking.setNamePatient(bookingUpdate.getNamePatient());
			booking.setNumberPhone(bookingUpdate.getNumberPhone());
			booking.setAddress(bookingUpdate.getAddress());
			booking.setBirthdayYear(bookingUpdate.getBirthdayYear());
			booking.setPathology(bookingUpdate.getPathology());
			booking.setEmail(bookingUpdate.getEmail());
			booking.setGender(bookingUpdate.getGender());
			booking.setUser(user);
			booking.setNamePersonBooking(bookingUpdate.getNamePersinBooking());
			booking.setIsExit(true);
			
			bookingRepository.save(booking);
			return new DataResponse(true, new Data("Đặt Lịch thành công !",HttpStatus.OK.value(),booking));
		}
		
		return new DataResponse(false, new Data("Lịch không tồn tại hoặc đã có người đặc !",HttpStatus.BAD_REQUEST.value()));
	}

	@Override
	public DataResponse getBookedBooking(UserPrincipal currentUser, String idClinic) {
		Clinic clinic = clinicRepository.findByIdClinicAndIdUser(idClinic, currentUser.getId());
		if(clinic != null) {
			List<Booking> bookings = bookingRepository.getBookedsByIdClincAndIdExpert(idClinic, currentUser.getId(), true);
			
			List<BookingResponseBooked> bookeds = new ArrayList<BookingResponseBooked>();
			for (Booking booking : bookings) {
				BookingResponseBooked booked = new BookingResponseBooked();
				booked.setId(booking.getId());
				booked.setNamePatient(booking.getNamePatient());   booked.setNamePersonBooking(booking.getNamePersonBooking());
				booked.setDateBooking(booking.getDateBooking());   booked.setNumberPhone(booking.getNumberPhone());
				booked.setPathology(booking.getPathology());   booked.setAddress(booking.getAddress()); 
				booked.setBirthdayYear(booking.getBirthdayYear());  booked.setEmail(booking.getEmail());
				booked.setGender(booking.getGender());    booked.setIsActive(booking.isActive());
				booked.setIsExit(booking.getIsExit());    booked.setTimeBooking(booking.getTimeBooking());
				booked.setUserBooked(new UserResponse(booking.getUser()));
				
				bookeds.add(booked);
			}
			return new DataResponse(true, new Data("Lấy thành công danh sách đã đặt lịch !",HttpStatus.OK.value(),bookeds));
		}
		return new DataResponse(false, new Data("Không phải bác sỹ hoặc phòng khám không tồn tại !",HttpStatus.BAD_REQUEST.value()));
	}

	@Override
	public DataResponse getBookedBooking(UserPrincipal currentUser) {
		
		List<Booking> bookings = bookingRepository.getBookedsByIdClinForUser(currentUser.getId(), true);
		if(bookings != null) {
			List<DoctorResponse> doctorResponses = new ArrayList<DoctorResponse>();
			for (Booking booking : bookings) {
				User doctor = userRepository.getOne(booking.getCreatedBy());
				Clinic clinic = booking.getClinic();
				Attachment attachment =   AttacchmetFunction.getAttachmentPerson(doctor.getAttachments(), "DAIDIEN");
		
				
				List<Comment> commentExperts = commentRepositiry.getCommnetsByIdClincAndIdExpert(clinic.getId(),doctor.getId());
				
				List<Booking> bookingExperts = bookingRepository.getBookedsByIdClincAndIdExpert(clinic.getId(),doctor.getId(),true);
				
				Set<Rate> rateExperts = rateRepository.getRatesByIdClincAndIdExpert(clinic.getId(),doctor.getId());
				Double countRate = RateFunction.getRateDoctor(rateExperts);
				
				String timeBooked = booking.getTimeBooking()+" "+booking.getDateBooking().toString();
				DoctorResponse doctorResponse = new DoctorResponse(doctor.getId(), doctor.getCreateAt(), 
						doctor.getUpdateAt(), doctor.getCreatedBy(), doctor.getUpdatedBy(), doctor.getDeletedBy(),
						doctor.getFullName(), doctor.getBirthday(), doctor.getGender(), doctor.getAge(), 
						doctor.getEmail(), doctor.getAddress(), doctor.getMobile(), doctor.getAbout(), 
						doctor.getFacebook(), new ClinicResponse(clinic), doctor.getFaculties(), doctor.getDegrees(),
						attachment,commentExperts.size(),bookingExperts.size(),countRate,timeBooked);
				doctorResponses.add(doctorResponse);
			}
			if(doctorResponses.size() == 1) {
				doctorResponses.add(doctorResponses.get(0));
				doctorResponses.add(doctorResponses.get(0));
				doctorResponses.add(doctorResponses.get(0));
			}if(doctorResponses.size() == 2) {
				doctorResponses.add(doctorResponses.get(1));
				doctorResponses.add(doctorResponses.get(0));
			}if(doctorResponses.size() == 3) {
				doctorResponses.add(doctorResponses.get(2));
			}
			return new DataResponse(true, new Data("lấy danh sách bác sỹ thành công !!",HttpStatus.OK.value(),doctorResponses));
		}
		return new DataResponse(false, new Data("Không phải bác sỹ hoặc phòng khám không tồn tại !",HttpStatus.BAD_REQUEST.value()));
	}

	@Override
	public DataResponse deleteBooking(String id) {
		Booking booking = bookingRepository.getOne(id);
		
		if(booking != null) {
			bookingRepository.delete(booking);
			return new DataResponse(true, new Data("Xóa thành công !!",HttpStatus.OK.value()));
		}
		return new DataResponse(false, new Data("Xóa không thành công !!",HttpStatus.BAD_REQUEST.value()));
	}

}
