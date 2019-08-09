package com.foody.services;

import java.util.List;

import com.foody.dto.RoleRequest;
import com.foody.entities.Role;
import com.foody.payload.DataResponse;

public interface RoleService {
	
	DataResponse createRole(RoleRequest roleRequest);
	
	Role getRoleByName(String name);
	
	Role getRoleById(String id);
	
	DataResponse deleteRole(String id);
	
	DataResponse deleteRoles(List<String> ids);
	
	DataResponse getRoleAll();
	
	DataResponse updateRole(String id, RoleRequest roleRequest);
}
