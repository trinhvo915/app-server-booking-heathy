package com.foody.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foody.entities.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, String>, CrudRepository<Clinic, String>{
	
	@Query(value ="Select * from clinic LEFT JOIN user_clinic ON clinic.id = user_clinic.id_clinic where clinic.id = :id_clinic and user_clinic.id_user = :id_user", nativeQuery=true)
	Clinic findByIdClinicAndIdUser(@Param("id_clinic") String id_clinic, @Param("id_user")String id_user);
	
	@Query(value ="Select * from clinic LEFT JOIN user_clinic ON clinic.id = user_clinic.id_clinic where user_clinic.id_user = :id_user", nativeQuery=true)
	List<Clinic> showClinicWithIdUser( @Param("id_user")String id_user);
	
	@Query(nativeQuery=true,value="SELECT * FROM clinic WHERE address_query LIKE CONCAT('%',:addressQuery,'%')")
	List<Clinic> searchClinic(@Param("addressQuery") String addressQuery);
}
