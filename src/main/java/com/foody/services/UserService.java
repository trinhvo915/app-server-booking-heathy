package com.foody.services;

import java.util.List;
import java.util.Optional;

import com.foody.dto.DoctorRegisterRequest;
import com.foody.dto.UserResponse;
import com.foody.dto.UserUpdate;
import com.foody.entities.User;
import com.foody.payload.DataResponse;

public interface UserService{
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByIdIn(List<String> userIds);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	User save(User user);
	
	User findByCode(String code);
	
	List<User> findByIds(List<String> ids);
	
	DataResponse updateUser(String id, DoctorRegisterRequest doctorRegisterRequest);
	
	DataResponse updateUserUpdate(UserUpdate userUpdate);
	
	UserResponse getUserByIdAndCheckRole(String id_user);
	
	DataResponse getAllDoctor();
	
	DataResponse searchClinic(String addressQuery);
	
	DataResponse reportUser(String idUser, String idDoctor,String idBooked);
	
	DataResponse getAllUser();
	
	DataResponse geUserprofile(String idUser);
}
