package com.foody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String>, CrudRepository<Booking, String>{

}
