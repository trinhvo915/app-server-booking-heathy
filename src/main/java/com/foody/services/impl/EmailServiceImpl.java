package com.foody.services.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.foody.dto.BookingRequestUpdate;
import com.foody.entities.Booking;
import com.foody.entities.User;
import com.foody.repository.BookingRepository;
import com.foody.repository.UserRepository;
import com.foody.services.EmailService;
import com.foody.services.UserService;
import com.foody.utils.Constant;
// muốn send mail phải bất chế độ bên gmail
//http://toidicodeo.blogspot.com/2018/10/1.html
@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	UserService userservice;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public void sendEmail(String email, HttpServletRequest httpRequest, User user) {
		user.setCode(UUID.randomUUID().toString());
		userservice.save(user);
		
		String appUrl = httpRequest.getScheme() + "://" +httpRequest.getServerName()+":"+httpRequest.getServerPort();
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setSubject(Constant.TEXT_CHANGE_PASSWORD);
		mailMessage.setFrom(Constant.EMAIL);
		mailMessage.setTo(user.getEmail());

		mailMessage.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token=" + user.getCode());
		
		mailSender.send(mailMessage);
	}

	@Override
	public void sendEmailBookingClient(BookingRequestUpdate bookingUpdate) {
		SimpleMailMessage mailMessageClient = new SimpleMailMessage();
		mailMessageClient.setSubject("[ ĐẶT LỊCH KHÁM BỆNH TỪ ỨNG DỤNG BOOKING CLINIC ]");
		mailMessageClient.setFrom(Constant.EMAIL);
		mailMessageClient.setTo(bookingUpdate.getEmail());
		
		mailMessageClient.setText("Chào bạn "+bookingUpdate.getNamePersinBooking()+".\n Chúng tôi từ ứng dụng Booking Clinic.\n"
				+ "Thông báo bạn đã đặt lịch tại phòng khám "+ bookingUpdate.getNameClinc()+" với Bác Sỹ "+ bookingUpdate.getNameDoctor() +".\n"
						+ "Thời gian khám bệnh : Ngày "+bookingUpdate.getDateBooking()+", Giờ "+bookingUpdate.getTimeBooking()+".\n"
								+ "Địa chỉ phòng khám : "+bookingUpdate.getAddressClinic()+".\nHãy đến đúng giờ ! Xin cảm ơn !");
		
		mailSender.send(mailMessageClient);
		
	}


	@Override
	public void sendEmailBookingDoctor(BookingRequestUpdate bookingUpdate) {
		
		SimpleMailMessage mailMessageDoctor = new SimpleMailMessage();
		
		User doctor = userRepository.getOne(bookingUpdate.getIdDoctor());
		mailMessageDoctor.setSubject("[ ĐẶT LỊCH KHÁM BỆNH TỪ ỨNG DỤNG BOOKING CLINIC ]");
		mailMessageDoctor.setFrom(Constant.EMAIL);
		mailMessageDoctor.setTo(doctor.getEmail());
		
		mailMessageDoctor.setText("Chào bạn "+doctor.getFullName()+".\n Chúng tôi từ ứng dụng Booking Clinic.\n"
				+ "Thông báo "+bookingUpdate.getNamePersinBooking()+" đã đặt lịch tại phòng khám "+ bookingUpdate.getNameClinc()+" với Bác Sỹ "+ bookingUpdate.getNameDoctor() +".\n "
						+ "Số điện thoại người đặt "+bookingUpdate.getNumberPhone()+".\nĐịa chỉ bệnh nhân : "+bookingUpdate.getAddress()
						+ ".\nThời gian khám bệnh : Ngày "+bookingUpdate.getDateBooking()+", Giờ "+bookingUpdate.getTimeBooking()+".\n");
	
		mailSender.send(mailMessageDoctor);
	}

	@Override
	public void sendEmailBookingBussy(String idDoctor, String idBooked) {
		SimpleMailMessage mailMessageClient = new SimpleMailMessage();
		Booking booked = bookingRepository.getOne(idBooked);
		User doctor = userRepository.getOne(idDoctor);
		if(doctor.getRoles().size() > 1 && !booked.getEmail().equals("")) {
			mailMessageClient.setSubject("[ ĐẶT LỊCH KHÁM BỆNH TỪ ỨNG DỤNG BOOKING CLINIC ]");
			mailMessageClient.setFrom(Constant.EMAIL);
			mailMessageClient.setTo(booked.getEmail());
			
			mailMessageClient.setText("Chào bạn "+booked.getNamePersonBooking()+".\n Chúng tôi từ ứng dụng Booking Clinic.\nThông báo đến bạn lịch bạn đặt, đã hủy vì bác sĩ có lịch bận ! \n"
					+ "Thông báo bạn đã đặt lịch tại phòng khám "+ booked.getClinic().getName()+" với Bác Sỹ "+ doctor.getFullName() +".\n"
							+ "Thời gian khám bệnh : Ngày "+booked.getDateBooking()+", Giờ "+booked.getTimeBooking()+".\n"
									+ "Địa chỉ phòng khám : "+booked.getClinic().getAddress()+".\nThật sự xin lỗi cho bất tiện này !");
			
			mailSender.send(mailMessageClient);
		}
	}
	
}
