package com.foody.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foody.dto.DoctorRegisterRequest;
import com.foody.entities.Degree;
import com.foody.entities.ExpertCode;
import com.foody.entities.Faculty;
import com.foody.entities.Role;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.DegreeRepository;
import com.foody.repository.ExpertCodeRepository;
import com.foody.repository.FacultyRepository;
import com.foody.repository.UserRepository;
import com.foody.services.RoleService;
import com.foody.services.UserService;
import com.foody.utils.Constant;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ExpertCodeRepository expertCodeRepository;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@Autowired
	DegreeRepository degreeRepository;
	
	@Autowired
	RoleService roleService;
	
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByUsernameOrEmail(String username, String email) {
		return userRepository.findByUsernameOrEmail(username, email);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findByIdIn(List<String> userIds) {
		return userRepository.findByIdIn(userIds);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByCode(String code) {
		User user = userRepository.findByCode(code);
		if(user != null) {
			return user;
		}
		return null;
	}

	@Override
	public List<User> findByIds(List<String> ids) {
		List<User> users = userRepository.findByIdIn(ids);
		if(users != null) {
			return users;
		}
		return null;
	}

	@Override
	public DataResponse updateUser(String id, DoctorRegisterRequest doctorRegisterRequest) {
		String token = doctorRegisterRequest.getTokenCode();
		ExpertCode checkToken = expertCodeRepository.getExpertCode(token,true);
		if(checkToken == null) {
			System.out.println("kakakakak");
			return new DataResponse(false, new Data(Constant.TOKEN_NO_FIND_ID,HttpStatus.BAD_REQUEST.value()));
		}
		
		if(!"".equals(id)) {
			List<String> ids = new ArrayList<String>() ;
			ids.add(id);
			List<User> users = findByIds(ids);
			if(users.size() != 0) {
				User user = users.get(0);
				
				user.setFullName(doctorRegisterRequest.getFullName());
				user.setBirthday(doctorRegisterRequest.getBirthday());
				user.setGender(doctorRegisterRequest.getGender());
				user.setAge(doctorRegisterRequest.getAge());
				user.setAddress(doctorRegisterRequest.getAddress());
				user.setEmail(doctorRegisterRequest.getEmail());
				user.setMobile(doctorRegisterRequest.getMobile());
				user.setAbout(doctorRegisterRequest.getAbout());
				
				List<String> idFaculties = new ArrayList<String>();
				for (Faculty faculty : doctorRegisterRequest.getFaculties()) {
					idFaculties.add(faculty.getId());
				}
				Set<Faculty>  faculties = facultyRepository.findByIdIn(idFaculties);
				user.setFaculties(faculties);
				
				List<String> idDegrees = new ArrayList<String>();
				for (Degree degree : doctorRegisterRequest.getDegrees()) {
					idDegrees.add(degree.getId());
				}
				Set<Degree>  degrees = degreeRepository.findByIdIn(idDegrees);
				user.setDegrees(degrees);
				
				Set<Role> roles = new HashSet<>();
				Role userRole = roleService.getRoleByName(Constant.USER);
				Role expertRole = roleService.getRoleByName(Constant.EXPERT);
				roles.add(userRole);
				roles.add(expertRole);
				user.setRoles(roles);
				
				userRepository.save(user);
				
				ExpertCode expertCode = expertCodeRepository.getExpertCode(token,true);
				expertCode.setActive(false);
				expertCodeRepository.save(expertCode);
				return new DataResponse(true, new Data(Constant.REGISTER_DOCTOR_SUCCESS,HttpStatus.OK.value(),user));
			}else {
				return new DataResponse(false, new Data(Constant.USER_NO_FIND_ID,HttpStatus.BAD_REQUEST.value()));
			}
		}
		return new DataResponse(false, new Data(Constant.REGISTER_DOCTOR_UNSUCCESS,HttpStatus.BAD_REQUEST.value()));
	}
}
