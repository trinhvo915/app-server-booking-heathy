package com.foody.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.foody.entities.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, String>, CrudRepository<Rate, String>{

	@Query(value ="Select * from rate r where r.id_user = :id_user and r.id_expert = :id_expert",nativeQuery=true)
	Rate checkUserAndDoctorForRate(@Param("id_user") String id_user, @Param("id_expert") String id_expert);
	
	@Query(value ="Select * from rate where  rate.id_clinic = :id_clinic and rate.id_expert = :id_expert", nativeQuery=true)
	Set<Rate> getRatesByIdClincAndIdExpert(@Param("id_clinic") String id_clinic, @Param("id_expert") String id_expert);
}
