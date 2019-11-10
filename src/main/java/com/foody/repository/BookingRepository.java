package com.foody.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.foody.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String>, CrudRepository<Booking, String>{
	
	@Query(value ="Select * from booking where  booking.id_clinic = :id_clinic and booking.id_expert = :id_expert", nativeQuery=true)
	Set<Booking> getBookingsByIdClincAndIdExpert(@Param("id_clinic") String id_clinic, @Param("id_expert") String id_expert);
	
	@Query(value ="Select * from booking where  booking.id_clinic = :id_clinic and booking.id_expert = :id_expert and booking.is_exit = :is_exit", nativeQuery=true)
	Set<Booking> getBookedsByIdClincAndIdExpert(@Param("id_clinic") String id_clinic, @Param("id_expert") String id_expert,@Param("is_exit") boolean is_exit);
}
