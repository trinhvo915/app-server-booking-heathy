package com.foody.services;

import java.util.List;
import java.util.Optional;

import com.foody.entities.User;

public interface UserService{
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByIdIn(List<String> userIds);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	User save(User user);
}
