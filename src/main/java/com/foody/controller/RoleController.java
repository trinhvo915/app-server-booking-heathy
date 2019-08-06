package com.foody.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.entities.Role;
import com.foody.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> createRole (@Valid @RequestBody Role role){
		if(role != null) {
			Role roleCreate = roleService.createRole(role);
			return new ResponseEntity<Role>(roleCreate, HttpStatus.CREATED) ;
		}
		String message = "Create role unsuccessfull";
		return new ResponseEntity(message,HttpStatus.NOT_FOUND);
	}
	
}
