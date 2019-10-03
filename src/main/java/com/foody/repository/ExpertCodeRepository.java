package com.foody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foody.entities.ExpertCode;

public interface ExpertCodeRepository extends JpaRepository<ExpertCode,String>{
	
	@Query(value ="Select * from expert_code e where e.token_code = :tokenCode", nativeQuery=true)
	ExpertCode getExpertCode(@Param("tokenCode") Integer tokenCode);
}
