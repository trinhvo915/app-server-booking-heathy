package com.foody.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foody.entities.Degree;
import com.foody.repository.DegreeRepository;
import com.foody.services.DegreeService;

@Service
public class DegreeServiceImpl implements DegreeService{

	@Autowired
	DegreeRepository degreeRepository;
	
	@Override
	public Set<Degree> getById(List<String> ids) {
		return degreeRepository.findByIdIn(ids);
	}

	@Override
	public List<Degree> getAll() {
		return  degreeRepository.findAll();
	}

}
