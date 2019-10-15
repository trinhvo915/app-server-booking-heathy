package com.foody.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.CommentRequest;
import com.foody.entities.Comment;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.CommentRepositiry;
import com.foody.repository.UserRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.CommentService;
import com.foody.utils.Constant;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommentRepositiry commentRepositiry;
	
	@Override
	public DataResponse addComment(UserPrincipal currentUser, CommentRequest commentRequest) {
		User user = userRepository.getOne(currentUser.getId());
		
		if(user != null) {
			Comment comment = new Comment();
			comment.setContent(commentRequest.getContent());
			
			User expert = userRepository.getOne(commentRequest.getExpert().getId());
			comment.setExpert(expert);
			
			comment.setUser(user);
			commentRepositiry.save(comment);		
			return new DataResponse(true, new Data(Constant.REGISTER_COMMENT_SUCCESS,HttpStatus.OK.value(),commentRequest));
		}
		return new DataResponse(false, new Data(Constant.REGISTER_COMMENT_UNSUCCESS,HttpStatus.BAD_REQUEST.value()));
	}

}
