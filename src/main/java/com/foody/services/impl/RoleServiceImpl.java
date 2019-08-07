package com.foody.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.RoleRequest;
import com.foody.entities.Role;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.RoleRepository;
import com.foody.services.RoleService;
import com.foody.utils.Constant;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public DataResponse createRole(RoleRequest roleRequest) {
		Role roleFindName =  getRoleByName(roleRequest.getName());
		
		if(roleFindName != null) {
			return new DataResponse(false, new Data(Constant.NAME_ROLE_EXIST,HttpStatus.BAD_REQUEST.toString(),null));
		}else {
			Role roleCreate = new RoleRequest().setRole(roleRequest);
			roleRepository.save(roleCreate);
			return new DataResponse(true, new Data(Constant.CREATE_ROLE_SUCCES,HttpStatus.CREATED.toString(),roleCreate));
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

	@Override
	public DataResponse deleteRole(String id) {
		if(!"".equals(id)) {
		
			Optional<Role> role = getRoleById(id);
			if(role != null) {
				roleRepository.deleteById(id);
				return new DataResponse(true, new Data(Constant.DELETE_ROLE_SUCCES,HttpStatus.OK.toString()));
			}else {
				return new DataResponse(false, new Data(Constant.DELETE_ROLE_NO_FIND_ID,HttpStatus.BAD_REQUEST.toString()));
			}
		}
		return new DataResponse(false, new Data(Constant.DELETE_ROLE_NOT_NULL_ID,HttpStatus.BAD_REQUEST.toString()));
	}

	@Override
	public DataResponse deleteRoles(List<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Role> getRoleById(String id) {
		if(!"".equals(id)) {
			Optional<Role> role = roleRepository.findById(id);
			return role;
		}
		return null;
	}

	@Override
	public DataResponse getRoleAll() {
		List<Role> roles = roleRepository.findAll();
		if(roles!=null) {
			return new DataResponse(true, new Data(Constant.GET_ALL_ROLE_CUCCES,HttpStatus.OK.toString(),roles));
		}
		return new DataResponse(false, new Data(Constant.GET_ALL_ROLE_UNCESSES,HttpStatus.BAD_REQUEST.toString(),roles));
	}
	
}