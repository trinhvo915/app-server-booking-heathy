package com.foody.services.impl;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import com.foody.entities.Attachment;
import com.foody.entities.AttachmentType;
import com.foody.entities.User;
import com.foody.repository.AttachmentTypeRepository;
import com.foody.repository.AttachmentRepository;
import com.foody.exception.FileStorageException;
import com.foody.exception.MyFileNotFoundException;
import com.foody.repository.UserRepository;
import com.foody.security.UserPrincipal;
import com.foody.services.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	AttachmentRepository attachmentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AttachmentTypeRepository attachmentTypeRepository;
	
	@Override
	public Attachment storeFile(UserPrincipal currentUser, MultipartFile file, String attachmentType) {
		
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        User user = userRepository.getOne(currentUser.getId());
        AttachmentType attType =  attachmentTypeRepository.findByName(attachmentType);
        
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Attachment dbFile = new Attachment(fileName, file.getContentType(), file.getBytes(), user, Collections.singleton(attType));

            return attachmentRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}

	@Override
	public Attachment getFile(String fileId) {
		 return attachmentRepository.findById(fileId)
	                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	    }

}
