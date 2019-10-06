package com.foody.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Degree;

@Repository
public interface DegreeRepository extends CrudRepository<Degree,String>{
	
	Set<Degree> findByIdIn(List<String> userIds);
}
