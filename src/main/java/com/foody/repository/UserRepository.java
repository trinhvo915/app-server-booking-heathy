package com.foody.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foody.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByIdIn(List<String> userIds);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	//SELECT e FROM Customer e WHERE e.name = :name
	@Query(value ="SELECT u FROM User u WHERE u.code = :code")
	User findByCode(@Param("code") String code);
}
