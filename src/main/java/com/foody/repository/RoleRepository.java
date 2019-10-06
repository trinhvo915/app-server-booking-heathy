package com.foody.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,String>{
	Role findByName(String name);
}
