package com.foody.utils;

import java.util.HashSet;
import java.util.Set;

import com.foody.entities.Attachment;
import com.foody.entities.AttachmentType;

public class AttacchmetFunction {

	public static  Set<Attachment> getAttachmentClinicPhotos (Set<Attachment> attachments, String typePhoto){
		Set<Attachment> attachmentClinics = new HashSet<Attachment>();
		for (Attachment attachment : attachments) {
			for (AttachmentType attachmentType : attachment.getAttachmentTypes()) {
				if(attachmentType.getName().equals(typePhoto)) {
					attachmentClinics.add(attachment);
				}
			}
		}
		return attachmentClinics;
	}
	
	public static  Attachment getAttachmentPerson (Set<Attachment> attachments, String typePhoto){
		Attachment attachmentp = new Attachment();
		@SuppressWarnings("unused")
		int flag = 0;
		for (Attachment attachment : attachments) {
			for (AttachmentType attachmentType : attachment.getAttachmentTypes()) {
				if(attachmentType.getName().equals(typePhoto)) {
					attachmentp.setId(attachment.getId());
					attachmentp.setData(attachment.getData());
					attachmentp.setCreatedBy(attachment.getCreatedBy());
					attachmentp.setFileName(attachment.getFileName());
					attachmentp.setAttachmentTypes(attachment.getAttachmentTypes());
					flag++;
					break;
				}
			}
		}
		return attachmentp;
	}
}
