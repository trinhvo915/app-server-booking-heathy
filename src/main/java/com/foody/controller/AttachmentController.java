package com.foody.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foody.dto.UploadFileResponse;
import com.foody.entities.Attachment;
import com.foody.security.CurrentUser;
import com.foody.security.UserPrincipal;
import com.foody.services.AttachmentService;

@RestController
@RequestMapping("/api")
public class AttachmentController {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);
	
	@Autowired
	AttachmentService attachmentService;
	
	@RequestMapping(value = "user/uploadFile", method = RequestMethod.POST)
	public UploadFileResponse uploadFile(@CurrentUser UserPrincipal currentUser, @RequestParam("file") MultipartFile file, String attachmentType) {
		
        Attachment attachmentFile = attachmentService.storeFile(currentUser,file,attachmentType);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(attachmentFile.getId())
                .toUriString();

        return new UploadFileResponse(attachmentFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@RequestMapping(value = "user/uploadMultipleFiles", method = RequestMethod.POST)
    public List<UploadFileResponse> uploadMultipleFiles(@CurrentUser UserPrincipal currentUser, @RequestParam("files") MultipartFile[] files,String attachmentType) {
		return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(currentUser,file,attachmentType))
                .collect(Collectors.toList());
	}
	
	@RequestMapping(value = "downloadFile/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@CurrentUser UserPrincipal currentUser, @PathVariable String fileId) {
        // Load file from database
		Attachment dbFile = attachmentService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
}
