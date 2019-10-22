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
import com.foody.dto.DoctorResponse;
import com.foody.dto.UserResponse;
import com.foody.entities.Clinic;
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
	
	@Override
	public UserResponse getUserByIdAndCheckRole(String id_user, String nameRole) {
		User user = userRepository.findByIdAndCheckRole(id_user, true,nameRole);
		return new UserResponse(user);
	}

	@Override
	public DataResponse getAllDoctor() {
		List<User> users = userRepository.getAllDoctor(true, "EXPERT");
		List<DoctorResponse> doctorResponses = new ArrayList<DoctorResponse>();
		for (User item : users) {
			for (Clinic clinic : item.getClinics()) {
				DoctorResponse doctorResponse = new DoctorResponse(item.getId(), item.getCreateAt(), 
						item.getUpdateAt(), item.getCreatedBy(), item.getUpdatedBy(), item.getDeletedBy(),
						item.getFullName(), item.getBirthday(), item.getGender(), item.getAge(), 
						item.getEmail(), item.getAddress(), item.getMobile(), item.getAbout(), 
						item.getFacebook(), clinic, item.getFaculties(), item.getDegrees());
				doctorResponses.add(doctorResponse);
			}
		}
		if(users == null) {
			return new DataResponse(false, new Data("Không tìm thấy bác sỹ !!",HttpStatus.BAD_REQUEST.value()));
		}
		return new DataResponse(true, new Data("lấy danh sách bác sỹ thành công !!",HttpStatus.OK.value(),doctorResponses));
	}
}
