package com.foody.services;

import com.foody.dto.ClinicsRequest;
import com.foody.dto.CommentRequest;
import com.foody.payload.DataResponse;
import com.foody.security.UserPrincipal;

public interface CommentService {
	
	DataResponse addComment(UserPrincipal currentUser, CommentRequest commentRequest);
	
	DataResponse getListCommentDoctor(ClinicsRequest clinicsRequest);
}
