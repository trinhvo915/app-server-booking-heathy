package com.foody.services;

import java.util.List;
import java.util.Set;

import com.foody.entities.Degree;

public interface DegreeService {
	Set<Degree> getById(List<String> ids);
	
	List<Degree> getAll();
}
