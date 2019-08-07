package com.foody.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foody.dto.RoleRequest;
import com.foody.entities.Role;
import com.foody.payload.DataResponse;
import com.foody.repository.RoleRepository;
import com.foody.services.RoleService;
import com.foody.utils.Constant;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Role, DataResponse> createRole(RoleRequest roleRequest) {
		Role roleFindName =  getRoleByName(roleRequest.getName());
		
		if(roleFindName != null) {
			return (Map<Role, DataResponse>) new HashMap<Role, DataResponse>().put(null, new DataResponse(false,Constant.NAME_ROLE));
		}else {
			Role roleCreate = new RoleRequest().setRole(roleRequest);
			roleRepository.save(roleCreate);
			return (Map<Role, DataResponse>) new HashMap<Role, DataResponse>().put(roleCreate, new DataResponse(true,"Add success"));
		}
	}

	@Override
	public Role getRoleByName(String name) {
		Role roles = roleRepository.findByName(name);
		if(roles!=null) {
			return roles;
		}
		return null;
	}
	
}
