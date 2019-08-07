package com.foody.services;

import java.util.List;
import java.util.Optional;

import com.foody.dto.RoleRequest;
import com.foody.entities.Role;
import com.foody.payload.DataResponse;

public interface RoleService {
	
	DataResponse createRole(RoleRequest roleRequest);
	
	Role getRoleByName(String name);
	
	Optional<Role> getRoleById(String id);
	
	DataResponse deleteRole(String id);
	
	DataResponse deleteRoles(List<String> ids);
	
	DataResponse getRoleAll();
}
