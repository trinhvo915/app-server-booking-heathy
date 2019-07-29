package com.foody.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foody.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByIdIn(List<String> userIds);
	
	Boolean exitsByUsername(String username);
	
	Boolean exitsByEmail(String email);
	
}
