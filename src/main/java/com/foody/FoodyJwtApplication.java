package com.foody;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.foody.repository.UserRepository;
import com.foody.services.RoleService;
//import com.foody.entities.Role;
//import com.foody.entities.User;
//import com.foody.utils.Constant;

@SpringBootApplication
public class FoodyJwtApplication implements CommandLineRunner{
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(FoodyJwtApplication.class);
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FoodyJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User("admin12345", "123456789", "admin12345","admin12345@gmail.com","0974076085");
//		
//		Role admintRole = roleService.getRoleByName(Constant.ADMIN);
//		Role userRole = roleService.getRoleByName(Constant.USER);
//		Role expertRole = roleService.getRoleByName(Constant.EXPERT);
//		Set<Role> roles = new HashSet<>();
//		roles.add(admintRole);
//		roles.add(userRole);
//		roles.add(expertRole);
//		user.setRoles(roles);
//		
//		userRepository.save(user);
	}

}
