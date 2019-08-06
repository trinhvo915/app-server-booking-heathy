package com.foody.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foody.entities.Role;
import com.foody.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}
}
