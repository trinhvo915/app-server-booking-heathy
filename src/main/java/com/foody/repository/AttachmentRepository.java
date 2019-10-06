package com.foody.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Attachment;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, String>{

}
 