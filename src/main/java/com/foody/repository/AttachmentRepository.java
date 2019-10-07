package com.foody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String>, CrudRepository<Attachment, String>{

}
 