package com.foody.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foody.entities.Faculty;
import com.foody.repository.FacultyRepository;
import com.foody.services.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService{

	@Autowired
	FacultyRepository facultyRepository;
	
	@Override
	public Set<Faculty> getById(List<String> ids) {
		return facultyRepository.findByIdIn(ids);
	}

	@Override
	public List<Faculty> getAll() {
		return facultyRepository.findAll();
	}

}
