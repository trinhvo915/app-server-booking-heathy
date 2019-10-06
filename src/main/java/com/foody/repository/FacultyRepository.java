package com.foody.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Faculty;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty,String>{
	Set<Faculty> findByIdIn(List<String> userIds);
}
