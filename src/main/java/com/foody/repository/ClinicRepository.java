package com.foody.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Clinic;

@Repository
public interface ClinicRepository extends CrudRepository<Clinic, String>{

}
