package com.foody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.foody.entities.AttachmentType;

@Repository
public interface AttachmentTypeRepository extends JpaRepository<AttachmentType, String>, CrudRepository<AttachmentType,String>{
	AttachmentType findByName(String name);
	
//	@Query(value ="Select * from attachmentt_type LEFT JOIN attachment_attachmenttype ON attachmentt_type.id = attachment_attachmenttype.id_attachmenttype LEFT JOIN attachment ON attachment_attachmenttype.id_attachment = attachment.id LEFT JOIN user on attachment.id_user = user.id  where user.id = :id_user and user.is_active = :is_active and attachmentt_type.name =:type_attachment", nativeQuery=true)
//	AttachmentType getAttachmentTypeDoctorPersonal(@Param("id_user") String id_user, @Param("is_active")boolean is_active,@Param("type_attachment") String type_attachment);
}
