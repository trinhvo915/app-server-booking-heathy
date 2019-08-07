package com.foody.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foody.dto.RoleRequest;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.services.RoleService;
import com.foody.utils.Constant;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public DataResponse getAllRoles(){
		DataResponse deleteRole = roleService.getRoleAll();
		return deleteRole;
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<DataResponse> createRole (@Valid @RequestBody RoleRequest roleRequest){
		System.out.println("check id 1");
		if(!roleRequest.getName().isEmpty()) {
			System.out.println("check id");
			DataResponse roleCreate  = roleService.createRole(roleRequest);
			if(roleCreate.getData().getObject() != null) {
				return new ResponseEntity<>(roleCreate,HttpStatus.CREATED);
			}
			return new ResponseEntity<>(roleCreate,HttpStatus.BAD_REQUEST);
		}
		DataResponse roleCreateUnsucces = new DataResponse(false, new Data(Constant.ROLE_NAME_NOT_NULL,HttpStatus.BAD_REQUEST.toString(),null));
		return new ResponseEntity<>(roleCreateUnsucces,HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value= "{id}", method = RequestMethod.DELETE, produces = "application/json")
	public DataResponse deleteById(@PathVariable("id") String id){
		DataResponse deleteRole = roleService.deleteRole(id);
		return deleteRole;
	}
	
}
