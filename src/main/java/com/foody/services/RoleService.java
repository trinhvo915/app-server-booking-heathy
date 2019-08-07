package com.foody.services;

import java.util.Map;

import com.foody.dto.RoleRequest;
import com.foody.entities.Role;
import com.foody.payload.DataResponse;

public interface RoleService {
	
	Map<Role, DataResponse> createRole(RoleRequest roleRequest);
	
	Role getRoleByName(String name);
}
