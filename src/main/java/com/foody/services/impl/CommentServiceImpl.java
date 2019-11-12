package com.foody.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.foody.dto.ClinicsRequest;
import com.foody.dto.CommentRequest;
import com.foody.dto.CommentResponse;
import com.foody.entities.Attachment;
import com.foody.entities.Clinic;
import com.foody.entities.Comment;
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
import com.foody.utils.CommentFunction;
import com.foody.utils.Constant;

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
		
		if(userComment != null) {
			Comment comment = new Comment();
			comment.setContent(commentRequest.getContent());
			
			User expert = userRepository.getOne(commentRequest.getExpert().getId());
			comment.setExpert(expert);
			
			Clinic clinic = clinicRepository.getOne(commentRequest.getClinic().getId());
			comment.setClinic(clinic);
			
			comment.setUser(userComment);
			Comment commnet =  commentRepositiry.save(comment);
			
			CommentResponse response = new CommentResponse();
			
			response.setId(commnet.getId());
			response.setContent(commnet.getContent());
			response.setCreateAt(commnet.getCreateAt());
			response.setUpdateAt(commnet.getUpdateAt());
			response.setCreatedBy(commnet.getCreatedBy());
			response.setUpdatedBy(commnet.getUpdatedBy());
			Attachment attachment = AttacchmetFunction.getAttachmentPerson(commnet.getUser().getAttachments(), "DAIDIEN");
			response.setAttachment(attachment);
			return new DataResponse(true, new Data(Constant.REGISTER_COMMENT_SUCCESS,HttpStatus.OK.value(),response));
		}
		return new DataResponse(false, new Data(Constant.REGISTER_COMMENT_UNSUCCESS,HttpStatus.BAD_REQUEST.value()));
	}

	@Override
	public DataResponse getListCommentDoctor(ClinicsRequest clinicsRequest) {
		
		List<Comment> commentExperts = commentRepositiry.getCommnetsByIdClincAndIdExpert(clinicsRequest.getIdClinic(),clinicsRequest.getIdDoctor());
		List<CommentResponse> commentResponses = CommentFunction.getCommentDoctor(commentExperts);
		return new DataResponse(true, new Data("get comment success !",HttpStatus.OK.value(),commentResponses));
	}

}
