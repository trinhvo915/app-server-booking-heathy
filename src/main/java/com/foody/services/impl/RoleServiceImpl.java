package com.foody.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.RoleRequest;
import com.foody.entities.Role;
import com.foody.exception.ResourceNotFoundException;
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
			return new DataResponse(false, new Data(Constant.NAME_ROLE_EXIST,HttpStatus.BAD_REQUEST.value()));
		}else {
			Role roleCreate = new RoleRequest().setRole(roleRequest);
			roleRepository.save(roleCreate);
			return new DataResponse(true, new Data(Constant.CREATE_ROLE_SUCCES,HttpStatus.CREATED.value(),roleCreate));
		}
	}

	@SuppressWarnings("unused")
	@Override
	public Role getRoleByName(String name) {
		Role roles = roleRepository.findByName(name);
		System.out.println(roles.toString());
		if(roles!=null) {
			return roles;
		}
		return null;
	}

	@Override
	public DataResponse deleteRole(String id) {
		if(!"".equals(id)) {
			Role role = getRoleById(id);
			if(role != null) {
				roleRepository.deleteById(id);
				return new DataResponse(true, new Data(Constant.DELETE_ROLE_SUCCES,HttpStatus.OK.value()));
			}else {
				return new DataResponse(false, new Data(Constant.ROLE_NO_FIND_ID,HttpStatus.BAD_REQUEST.value()));
			}
		}
		return new DataResponse(false, new Data(Constant.ROLE_NOT_NULL_ID,HttpStatus.BAD_REQUEST.value()));
	}

	@Override
	public DataResponse deleteRoles(List<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getRoleById(String id) {
		if(!"".equals(id)) {
			Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
			return role;
		}
		return null;
	}

	@Override
	public DataResponse getRoleAll() {
		List<Role> roles = (List<Role>) roleRepository.findAll();
		if(roles!=null) {
			return new DataResponse(true, new Data(Constant.GET_ALL_ROLE_CUCCES,HttpStatus.OK.value(),roles));
		}
		return new DataResponse(false, new Data(Constant.GET_ALL_ROLE_UNCESSES,HttpStatus.BAD_REQUEST.value(),roles));
	}

	@Override
	public DataResponse updateRole(String id, RoleRequest roleRequest) {
		if(!"".equals(id)) {
			Role role = getRoleById(id);
			if(role != null) {
				role.setName(roleRequest.getName());
				roleRepository.save(role);
				return new DataResponse(true, new Data(Constant.UPDATE_ROLE_SUCCES,HttpStatus.OK.value(),role));
			}else {
				return new DataResponse(false, new Data(Constant.ROLE_NO_FIND_ID,HttpStatus.BAD_REQUEST.value()));
			}
		}
		return new DataResponse(false, new Data(Constant.ROLE_NOT_NULL_ID,HttpStatus.BAD_REQUEST.value()));
	}
	
}
