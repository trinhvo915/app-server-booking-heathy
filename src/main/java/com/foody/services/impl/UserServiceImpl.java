package com.foody.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.foody.dto.ClinicResponse;
import com.foody.dto.DoctorRegisterRequest;
import com.foody.dto.DoctorResponse;
import com.foody.dto.UserResponse;
import com.foody.entities.Attachment;
import com.foody.entities.AttachmentType;
import com.foody.entities.Clinic;
import com.foody.entities.Degree;
import com.foody.entities.ExpertCode;
import com.foody.entities.Faculty;
import com.foody.entities.Rate;
import com.foody.entities.Role;
import com.foody.entities.User;
import com.foody.entities.enums.NumberStar;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.repository.DegreeRepository;
import com.foody.repository.ExpertCodeRepository;
import com.foody.repository.FacultyRepository;
import com.foody.repository.UserRepository;
import com.foody.repository.ClinicRepository;
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
	
	@Autowired
	ClinicRepository clinicRepository;
	
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
				user.setEmail(user.getEmail());
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
	public UserResponse getUserByIdAndCheckRole(String id_user) {
		User user = userRepository.getUserAndRole(id_user, true);
		return new UserResponse(user);
	}

	@SuppressWarnings("unused")
	@Override
	public DataResponse getAllDoctor() {
		List<DoctorResponse> doctorResponses = new ArrayList<DoctorResponse>();
		List<Clinic> clinics = clinicRepository.findAll();
		for (Clinic clinic : clinics) {
			for (User item : clinic.getUsers()) {
				Attachment attachmentp = new Attachment();
				int flag = 0;
				for (Attachment attachment : item.getAttachments()) {
					for (AttachmentType attachmentType : attachment.getAttachmentTypes()) {
						if(attachmentType.getName().equals("DAIDIEN")) {
							attachmentp.setId(attachment.getId());
							attachmentp.setData(attachment.getData());
							attachmentp.setCreatedBy(attachment.getCreatedBy());
							attachmentp.setFileName(attachment.getFileName());
							attachmentp.setAttachmentTypes(attachment.getAttachmentTypes());
							flag++;
							break;
						}
						if(flag>0) {
							break;
						}
					}
				}
				
				Double countRate = 0.0; int fiveRate = 0 ; int fourRate = 0;
				int threeRate = 0; int twoRate = 0; int oneRate = 0;
				for (Rate rate : item.getRateExperts()) {
					if(rate.getNumberStar() == NumberStar.FIVE) {
						fiveRate++;
					}
					if(rate.getNumberStar() == NumberStar.FOUR) {
						fourRate++;
					}
					if(rate.getNumberStar() == NumberStar.THREE) {
						threeRate++;
					}
					if(rate.getNumberStar() == NumberStar.TWO) {
						twoRate++;
					}
					if(rate.getNumberStar() == NumberStar.ONE) {
						oneRate++;
					}
				}
				
				Double sumRate = (double) (fiveRate + fourRate + threeRate + twoRate + oneRate);
				if(sumRate == 0) {
					countRate = 1.0;
				}else {
					countRate = (5.0*fiveRate + 4.0*fourRate + 3.0*threeRate + 2.0*twoRate + 1.0*oneRate) / sumRate;
				}
				
				if(countRate>= 0.5 && countRate <1) {
					countRate = 0.5;
				}else if(countRate >=1.5 && countRate < 2) {
					countRate = 1.5;
				}else if(countRate >=2.5 && countRate < 3) {
					countRate = 2.5;
				}else if(countRate >=3.5 && countRate < 4) {
					countRate = 3.5;
				}else if(countRate >=4.5 && countRate < 5) {
					countRate = 4.5;
				}
				
				DoctorResponse doctorResponse = new DoctorResponse(item.getId(), item.getCreateAt(), 
						item.getUpdateAt(), item.getCreatedBy(), item.getUpdatedBy(), item.getDeletedBy(),
						item.getFullName(), item.getBirthday(), item.getGender(), item.getAge(), 
						item.getEmail(), item.getAddress(), item.getMobile(), item.getAbout(), 
						item.getFacebook(), new ClinicResponse(clinic), item.getFaculties(), item.getDegrees(),attachmentp,item.getCommentExperts().size(),item.getBookingExperts().size(),countRate);
				doctorResponses.add(doctorResponse);
			}
		}
		
		if(clinics == null) {
			return new DataResponse(false, new Data("Không tìm thấy bác sỹ !!",HttpStatus.BAD_REQUEST.value()));
		}
		return new DataResponse(true, new Data("lấy danh sách bác sỹ thành công !!",HttpStatus.OK.value(),doctorResponses));
	}
}
