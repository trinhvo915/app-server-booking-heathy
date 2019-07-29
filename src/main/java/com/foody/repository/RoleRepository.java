package com.foody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String>{
	Role findByName(String name);
}
