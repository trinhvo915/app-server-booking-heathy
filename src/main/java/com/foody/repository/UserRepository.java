package com.foody.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foody.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>, CrudRepository<User,String>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByIdIn(List<String> userIds);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	//SELECT e FROM Customer e WHERE e.name = :name
	@Query(value ="SELECT u FROM User u WHERE u.code = :code")
	User findByCode(@Param("code") String code);
	
	@Query(value ="Select * from user LEFT JOIN user_role ON user.id = user_role.id_user LEFT JOIN role ON user_role.id_role = role.id  where user.id = :id_user and user.is_active = :is_active and role.name = :name_role", nativeQuery=true)
	User findByIdAndCheckRole(@Param("id_user") String id_user, @Param("is_active")boolean is_active,@Param("name_role") String name_role);

	@Query(value ="Select * from user LEFT JOIN user_role ON user.id = user_role.id_user LEFT JOIN role ON user_role.id_role = role.id  where user.email = :email and user.is_active = :is_active and role.name = :name_role", nativeQuery=true)
	User findByEmailAndCheckRole(@Param("email") String email, @Param("is_active")boolean is_active,@Param("name_role") String name_role);

	@Query(value ="Select * from user LEFT JOIN user_role ON user.id = user_role.id_user LEFT JOIN role ON user_role.id_role = role.id  where user.is_active = :is_active and role.name = :name_role", nativeQuery=true)
	List<User> getAllDoctor(@Param("is_active")boolean is_active, @Param("name_role") String name_role);

	@Query(value ="Select * from user LEFT JOIN user_clinic ON user.id = user_clinic.id_user LEFT JOIN clinic ON user_clinic.id_clinic = clinic.id  where clinic.id = :id_clinic and user.is_active = :is_active", nativeQuery=true)
	Set<User> getUserByIdClinic(@Param("id_clinic") String id_clinic, @Param("is_active")boolean is_active);

	@Query(value ="Select * from user LEFT JOIN user_role ON user.id = user_role.id_user LEFT JOIN role ON user_role.id_role = role.id  where user.id = :id_user and user.is_active = :is_active", nativeQuery=true)
	User getUserAndRole(@Param("id_user") String id_user, @Param("is_active")boolean is_active);
}
