package com.foody.services.impl;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.CommentRequest;
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
import com.foody.repository.RateRepository;
import com.foody.repository.UserRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.CommentService;
import com.foody.utils.AttacchmetFunction;
import com.foody.utils.Constant;
import com.foody.utils.UserResponceClinicFunction;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepositiry commentRepositiry;
	
	@Autowired
	ClinicRepository clinicRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	RateRepository rateRepository;
	
	@Override
	public DataResponse addComment(UserPrincipal currentUser, CommentRequest commentRequest) {
		User userComment = userRepository.getOne(currentUser.getId());
		
		User user = userRepository.findByIdAndCheckRole(commentRequest.getExpert().getId(), true, "EXPERT");
		if(userComment != null) {
			Comment comment = new Comment();
			comment.setContent(commentRequest.getContent());
			
			User expert = userRepository.getOne(commentRequest.getExpert().getId());
			comment.setExpert(expert);
			
			Clinic clinic = clinicRepository.getOne(commentRequest.getClinic().getId());
			comment.setClinic(clinic);
			
			comment.setUser(userComment);
			commentRepositiry.save(comment);
			
			List<Comment> commentExperts = commentRepositiry.getCommnetsByIdClincAndIdExpert(clinic.getId(),user.getId());
			
			List<Booking> bookingExperts = bookingRepository.getBookingsByIdClincAndIdExpert(clinic.getId(),user.getId());
			
			Set<Rate> rateExperts = rateRepository.getRatesByIdClincAndIdExpert(clinic.getId(),user.getId());

			Attachment attachmentp = AttacchmetFunction.getAttachmentPerson(user.getAttachments(), "DAIDIEN");

			UserResponceClinic userClinic = UserResponceClinicFunction.setUserResponceClinic(user, clinic.getId(),commentExperts,bookingExperts,rateExperts,attachmentp);
			
			return new DataResponse(true, new Data(Constant.REGISTER_COMMENT_SUCCESS,HttpStatus.OK.value(),userClinic));
		}
		return new DataResponse(false, new Data(Constant.REGISTER_COMMENT_UNSUCCESS,HttpStatus.BAD_REQUEST.value()));
	}

}
