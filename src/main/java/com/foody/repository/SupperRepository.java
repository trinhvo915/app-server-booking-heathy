package com.foody.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.AuditEntity;

@Repository
public interface SupperRepository extends CrudRepository<AuditEntity,String>{
	
}
