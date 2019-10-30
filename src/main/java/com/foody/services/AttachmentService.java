package com.foody.services;

import org.springframework.web.multipart.MultipartFile;

import com.foody.entities.Attachment;
import com.foody.security.UserPrincipal;

public interface AttachmentService {
	
	Attachment storeFile(UserPrincipal currentUser, MultipartFile file,String attachmentType);
	
	Attachment getFile(String fileId);
}
