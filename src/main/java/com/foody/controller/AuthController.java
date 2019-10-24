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

import com.foody.dto.LoginRequest;
import com.foody.dto.SignUpRequest;
import com.foody.entities.Role;
import com.foody.entities.User;
import com.foody.payload.ApiResponse;
import com.foody.payload.Data;
import com.foody.payload.DataResponse;
import com.foody.payload.JwtAuthenticationResponse;
import com.foody.security.JwtTokenProvider;
import com.foody.services.RoleService;
import com.foody.services.UserService;
import com.foody.utils.Constant;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
	RoleService roleService;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;
	    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		Boolean isAvailableUsername = userService.existsByUsername(loginRequest.getUsernameOrEmail());
		Boolean isAvailableEmail = userService.existsByEmail(loginRequest.getUsernameOrEmail());
		
		if(!(isAvailableUsername || isAvailableEmail)) {
            return new ResponseEntity(new DataResponse(false, new Data(Constant.USERNAME_OR_PASWORD_NO_EXIST,HttpStatus.BAD_REQUEST.value())),
                    HttpStatus.BAD_REQUEST);
        }

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

	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
		
		if(userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, Constant.USERNAME_USER_EXIST));
        }

        if(userService.existsByEmail(signUpRequest.getEmail())) {
        	return ResponseEntity.badRequest().body(new ApiResponse(false, Constant.EMAIL_USER_EXIST));
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getPassword(),signUpRequest.getFullName(),
                signUpRequest.getEmail(), signUpRequest.getMobile());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.getRoleByName(Constant.USER);
               
        user.setRoles(Collections.singleton(userRole));

        User result = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
	
} 
