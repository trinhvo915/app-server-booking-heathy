package com.foody.services;

import com.foody.dto.PostRequest;
import com.foody.payload.DataResponse;
import com.foody.security.UserPrincipal;

public interface PostService {
	
	DataResponse addPostForClinic(PostRequest postRequest, UserPrincipal currentUser) ;
	
	DataResponse getPostTypePostForClinic(String idClinic, String typePost);
}
