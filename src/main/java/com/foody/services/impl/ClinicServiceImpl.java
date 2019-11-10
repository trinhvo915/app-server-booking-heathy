package com.foody.services.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.BookingResponse;
import com.foody.dto.ClinicRequest;
import com.foody.dto.ClinicResponceDoctors;
import com.foody.dto.ClinicResponse;
import com.foody.dto.ClinicsRequest;
import com.foody.dto.CommentResponse;
import com.foody.dto.UserResponceClinic;
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
import com.foody.repository.FacultyRepository;
import com.foody.repository.RateRepository;
import com.foody.repository.UserRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.ClinicService;
import com.foody.utils.AttacchmetFunction;
import com.foody.utils.BookingFunction;
import com.foody.utils.CommentFunction;
import com.foody.utils.Constant;
import com.foody.utils.RateFunction;


@Service
public class ClinicServiceImpl implements ClinicService{

	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@Autowired
	CommentRepositiry commentRepositiry;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	RateRepository rateRepository;
	
	@Override
	public DataResponse addClinicCurrentDoctor(UserPrincipal currentUser, ClinicRequest clinicRequest) {
		User user = userRepository.findByIdAndCheckRole(currentUser.getId(), true, Constant.EXPERT);
		if(user ==null) {
			return new DataResponse(false, new Data(Constant.NO_EXPERT,HttpStatus.BAD_REQUEST.value()));
		}
		
		if(!"".equals(currentUser.getId())) {
			Clinic clinic = new Clinic(clinicRequest.getName(), clinicRequest.getAddress(), clinicRequest.getLatitude(), clinicRequest.getLongitude());
			
			List<String> idFaculties = new ArrayList<String>();
			clinicRequest.getFaculties().stream().forEach( x -> idFaculties.add(x.getId()));
			
			clinic.setFaculties(facultyRepository.findByIdIn(idFaculties));
			
			Set<User> users = new HashSet<>();
			users.add(user);
			clinic.setUsers(users);
			
			clinicRepository.save(clinic);
			
			ClinicResponse clinicResponse = new ClinicResponse(clinic);
			return new DataResponse(true, new Data(Constant.REGISTER_CLINIC_SUCCESS,HttpStatus.OK.value(),clinicResponse));
		}else {
			return new DataResponse(false, new Data(Constant.USER_NO_FIND_ID,HttpStatus.BAD_REQUEST.value()));
		}
	}

	@Override
	public DataResponse addDoctorIntoClinic(UserPrincipal currentUser, String idClinic, String usernameOrEmail) {
		User user = userRepository.findByIdAndCheckRole(currentUser.getId(), true, "EXPERT");
		Clinic clinic = clinicRepository.findByIdClinicAndIdUser(idClinic,user.getId());
		User userAdd = userRepository.findByEmailAndCheckRole(usernameOrEmail, true, "EXPERT");
		
		if(clinic != null && userAdd != null){
			Set<User> users = userRepository.getUserByIdClinic(idClinic, true);
			users.add(userAdd);
			clinic.setUsers(users);
			
			clinicRepository.save(clinic);
			return new DataResponse(true, new Data(Constant.ADD_DOCTOR_SUCCESS,HttpStatus.OK.value(),clinic));
		}else {
			return new DataResponse(false, new Data(Constant.ADD_DOCTOR_UNSUCCESS,HttpStatus.BAD_REQUEST.value()));
		}
	}

	@Override
	public DataResponse getDoctorInClinic(ClinicsRequest clinicsRequest) {
		User user = userRepository.findByIdAndCheckRole(clinicsRequest.getIdDoctor(), true, "EXPERT");
		Clinic clinic = clinicRepository.findByIdClinicAndIdUser(clinicsRequest.getIdClinic(),clinicsRequest.getIdDoctor());
		
		ClinicResponceDoctors clinicResponceDoctors = new ClinicResponceDoctors();
		Set<UserResponceClinic> userResponceClinics = new HashSet<>();
		
		List<User> users = new ArrayList<> (clinic.getUsers());
		User userOwner = users.get(users.size()-1);
		Set<Attachment> attachmentClinics = AttacchmetFunction.getAttachmentClinicPhotos(userOwner.getAttachments(), "CLINIC");
		
		clinicResponceDoctors.setId(clinic.getId()); clinicResponceDoctors.setName(clinic.getName());
		clinicResponceDoctors.setAddress(clinic.getAddress()); clinicResponceDoctors.setLatitude(clinic.getLatitude());
		clinicResponceDoctors.setLongitude(clinic.getLongitude()); clinicResponceDoctors.setCreatedBy(clinic.getCreatedBy());
		clinicResponceDoctors.setCreateAt(clinic.getCreateAt()); clinicResponceDoctors.setFaculties(clinic.getFaculties());
		clinicResponceDoctors.setPrices(clinic.getPrices()); clinicResponceDoctors.setPhotoClinics(attachmentClinics);
		
		UserResponceClinic userResponceClinic = new UserResponceClinic();
		
		for (User userItem : clinic.getUsers()) {
			if(!userItem.getId().equals(user.getId())) {
				userResponceClinic.setId(userItem.getId()); userResponceClinic.setCreateAt(userItem.getCreateAt());
				userResponceClinic.setUpdateAt(userItem.getUpdateAt()); userResponceClinic.setCreatedBy(userItem.getCreatedBy());
				userResponceClinic.setUpdatedBy(userItem.getUpdatedBy()); userResponceClinic.setDeletedBy(userItem.getDeletedBy());
				userResponceClinic.setUsername(userItem.getUsername()); userResponceClinic.setFullName(userItem.getFullName());
				userResponceClinic.setBirthday(userItem.getBirthday()); userResponceClinic.setGender(userItem.getGender());
				userResponceClinic.setAge(userItem.getAge()); userResponceClinic.setBadPoint(userItem.getBadPoint());
				userResponceClinic.setEmail(userItem.getEmail()); userResponceClinic.setAddress(userItem.getEmail());
				userResponceClinic.setMobile(userItem.getMobile()); userResponceClinic.setAbout(userItem.getAbout());
				userResponceClinic.setFacebook(userItem.getFacebook()); userResponceClinic.setCode(userItem.getCode());
				userResponceClinic.setFaculties(userItem.getFaculties()); userResponceClinic.setDegrees(userItem.getDegrees());
				
				Set<Comment> commentExperts = commentRepositiry.getCommnetsByIdClincAndIdExpert(clinic.getId(),userItem.getId());
				Set<CommentResponse> commentResponses = CommentFunction.getCommentDoctor(commentExperts);
				userResponceClinic.setCommentExperts(commentResponses);
				
				Set<Booking> bookingExperts = bookingRepository.getBookingsByIdClincAndIdExpert(clinic.getId(),userItem.getId());
				Set<BookingResponse> bookingResponses = BookingFunction.getBookingDoctor(bookingExperts);
				userResponceClinic.setBookingExperts(bookingResponses);
				
				Set<Rate> rateExperts = rateRepository.getRatesByIdClincAndIdExpert(clinic.getId(),userItem.getId());
				Double countRate = RateFunction.getRateDoctor(rateExperts);
				userResponceClinic.setCountRate(countRate);
				
				Attachment attachmentp = AttacchmetFunction.getAttachmentPerson(userItem.getAttachments(), "DAIDIEN");
				userResponceClinic.setAttachmentPerson(attachmentp);
				
				userResponceClinics.add(userResponceClinic);
			}
		}
		
		UserResponceClinic userClinic = new UserResponceClinic();
		userClinic.setId(user.getId()); userClinic.setCreateAt(user.getCreateAt());
		userClinic.setUpdateAt(user.getUpdateAt()); userClinic.setCreatedBy(user.getCreatedBy());
		userClinic.setUpdatedBy(user.getUpdatedBy()); userClinic.setDeletedBy(user.getDeletedBy());
		userClinic.setUsername(user.getUsername()); userClinic.setFullName(user.getFullName());
		userClinic.setBirthday(user.getBirthday()); userClinic.setGender(user.getGender());
		userClinic.setAge(user.getAge()); userClinic.setBadPoint(user.getBadPoint());
		userClinic.setEmail(user.getEmail()); userClinic.setAddress(user.getEmail());
		userClinic.setMobile(user.getMobile()); userClinic.setAbout(user.getAbout());
		userClinic.setFacebook(user.getFacebook()); userClinic.setCode(user.getCode());
		userClinic.setFaculties(user.getFaculties()); userClinic.setDegrees(user.getDegrees());
		
		Set<Comment> commentExperts = commentRepositiry.getCommnetsByIdClincAndIdExpert(clinic.getId(),user.getId());
		Set<CommentResponse> commentResponses = CommentFunction.getCommentDoctor(commentExperts);
		userClinic.setCommentExperts(commentResponses);
		
		Set<Booking> bookingExperts = bookingRepository.getBookingsByIdClincAndIdExpert(clinic.getId(),user.getId());
		Set<BookingResponse> bookingResponses = BookingFunction.getBookingDoctor(bookingExperts);
		userClinic.setBookingExperts(bookingResponses);
		
		Set<Rate> rateExperts = rateRepository.getRatesByIdClincAndIdExpert(clinic.getId(),user.getId());
		Double countRate = RateFunction.getRateDoctor(rateExperts);
		userClinic.setCountRate(countRate);
		
		Attachment attachmentp = AttacchmetFunction.getAttachmentPerson(user.getAttachments(), "DAIDIEN");
		userClinic.setAttachmentPerson(attachmentp);
		
		userResponceClinics.add(userClinic);
		
		clinicResponceDoctors.setUserResponceClinics(userResponceClinics);
		
		return new DataResponse(true, new Data(Constant.ADD_DOCTOR_SUCCESS,HttpStatus.OK.value(),clinicResponceDoctors));
	}
	
}
