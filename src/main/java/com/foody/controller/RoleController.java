package com.foody.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.RoleRequest;
import com.foody.entities.Role;
import com.foody.payload.DataResponse;
import com.foody.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Map<Role, DataResponse> createRole (@Valid @RequestBody RoleRequest roleRequest){
		if(!roleRequest.getName().isEmpty()) {
			Map<Role, DataResponse> roleCreate  = roleService.createRole(roleRequest);
			return roleCreate ;
		}
		return (Map<Role, DataResponse>) new HashMap<Role, DataResponse>().put(null, new DataResponse(false,"Add unsuccess"));
	}
	
}
