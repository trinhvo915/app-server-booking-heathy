package com.foody.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foody.entities.Role;
import com.foody.entities.User;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.payload.JwtAuthenticationResponse;
import com.foody.payload.LoginRequest;
import com.foody.payload.SignUpRequest;
import com.foody.repository.RoleRepository;
import com.foody.repository.UserRepository;
import com.foody.security.JwtTokenProvider;
import com.foody.services.RoleService;
import com.foody.utils.Constant;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
	RoleService roleService;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
	    
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		System.out.println(loginRequest.getUsernameOrEmail());
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
		System.out.println(signUpRequest.getEmail());
		if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new DataResponse(false, new Data(Constant.USERNAME_USER_EXIST,HttpStatus.BAD_REQUEST.toString())),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        	return new ResponseEntity(new DataResponse(false, new Data(Constant.EMAIL_USER_EXIST,HttpStatus.BAD_REQUEST.toString())),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getPassword(),signUpRequest.getFistName(),
                signUpRequest.getLastName(), signUpRequest.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.getRoleByName(Constant.USER);
               
        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return new ResponseEntity<>(new DataResponse(true, new Data("User registered successfully", HttpStatus.CREATED.toString(), result)),HttpStatus.CREATED);
    }
	
} 
