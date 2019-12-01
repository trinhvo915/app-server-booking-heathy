package com.foody.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.foody.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String>, CrudRepository<Booking, String>{
	
	@Query(value ="Select * from booking where  booking.id_clinic = :id_clinic and booking.id_expert = :id_expert and booking.date_booking = :date_booking", nativeQuery=true)
	List<Booking> getBookingsByIdClincAndIdExpert(@Param("id_clinic") String id_clinic, @Param("id_expert") String id_expert, @Param("date_booking") Date date_booking);
	
	@Query(value ="Select * from booking where  booking.id_clinic = :id_clinic and booking.id_expert = :id_expert and booking.date_booking >= :date_booking", nativeQuery=true)
	List<Booking> getBookingsAllByIdClincAndIdExpert(@Param("id_clinic") String id_clinic, @Param("id_expert") String id_expert, @Param("date_booking") Date date_booking);
	
	@Query(value ="Select * from booking where  booking.id_clinic = :id_clinic and booking.id_expert = :id_expert and booking.is_exit = :is_exit", nativeQuery=true)
	List<Booking> getBookedsByIdClincAndIdExpert(@Param("id_clinic") String id_clinic, @Param("id_expert") String id_expert,@Param("is_exit") boolean is_exit);

	@Query(value ="Select * from booking where  booking.id_clinic = :id_clinic and booking.id_expert = :id_expert and booking.date_booking = :date_booking", nativeQuery=true)
	List<Booking> getBookingStringDatesByIdClincAndIdExpert(@Param("id_clinic") String id_clinic, @Param("id_expert") String id_expert, @Param("date_booking") String date_booking);

	@Query(value ="Select * from booking where  booking.id = :id and booking.id_expert = :id_expert and booking.is_exit = :is_exit", nativeQuery=true)
	Booking checkBookingWithIdBookingAnIdDoctor(@Param("id") String id, @Param("id_expert") String id_expert,@Param("is_exit") boolean is_exit);

	@Query(value ="Select * from booking where booking.id_user = :id_user and booking.is_exit = :is_exit", nativeQuery=true)
	List<Booking> getBookedsByIdClinForUser(@Param("id_user") String id_user,@Param("is_exit") boolean is_exit);
}
