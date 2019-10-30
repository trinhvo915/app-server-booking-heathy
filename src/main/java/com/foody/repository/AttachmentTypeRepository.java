package com.foody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.foody.entities.AttachmentType;

@Repository
public interface AttachmentTypeRepository extends JpaRepository<AttachmentType, String>, CrudRepository<AttachmentType,String>{
	AttachmentType findByName(String name);
}
