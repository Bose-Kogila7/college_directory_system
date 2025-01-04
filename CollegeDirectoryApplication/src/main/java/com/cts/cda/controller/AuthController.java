package com.cts.cda.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cda.entity.Role;
import com.cts.cda.entity.User;
import com.cts.cda.models.LoginModel;
import com.cts.cda.models.SignupModel;
import com.cts.cda.repository.RoleRepository;
import com.cts.cda.repository.UserRepository;
import com.cts.cda.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		return new ResponseEntity<String>(userService.verify(user), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerperson(@RequestBody User user) {

		if (userService.usernameExists(user.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		if (userService.emailExists(user.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}
		userService.saveUser(user);
		return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);

	}

}
