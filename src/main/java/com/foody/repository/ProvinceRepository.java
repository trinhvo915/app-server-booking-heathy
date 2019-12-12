package com.foody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String>, CrudRepository<Province, String>{

}
