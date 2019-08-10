package com.foody.services;

import org.springframework.web.multipart.MultipartFile;

import com.foody.entities.Attachment;

public interface AttachmentService {
	
	Attachment storeFile(MultipartFile file);
	
	Attachment getFile(String fileId);
}
