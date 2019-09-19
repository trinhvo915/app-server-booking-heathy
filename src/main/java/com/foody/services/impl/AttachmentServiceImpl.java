package com.foody.services.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import com.foody.entities.Attachment;
import com.foody.exception.FileStorageException;
import com.foody.exception.MyFileNotFoundException;
import com.foody.repository.AttachmentRepository;
import com.foody.services.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	@Autowired
	AttachmentRepository attachmentRepository;
	
	@Override
	public Attachment storeFile(MultipartFile file) {
		
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            System.out.println("8888888888888 : "+file.getBytes());
            Attachment dbFile = new Attachment(fileName, file.getContentType(), file.getBytes());

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