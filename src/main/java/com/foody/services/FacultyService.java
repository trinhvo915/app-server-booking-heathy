package com.foody.services;

import java.util.List;
import java.util.Set;

import com.foody.entities.Faculty;

public interface FacultyService {
	Set<Faculty> getById(List<String> ids);
	List<Faculty> getAll();
}
