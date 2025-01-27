package com.cts.cda.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.cts.cda.security.JWTService;
import com.cts.cda.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	   @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JWTService jwtService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody User loginRequest) {
//		System.out.println(user.getEmail());
//		return new ResponseEntity<String>(userService.verify(user), HttpStatus.OK);
//		 try {
//		 Authentication authentication = authenticationManager.authenticate(
//		            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
//		        );
//
//		        if (authentication.isAuthenticated()) {
//		            String token = jwtService.generateToken(user.getEmail());
//		            return new ResponseEntity<>(token, HttpStatus.OK);
//		        } else {
//		            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
//		        }
//		 }
//		        catch (Exception e) {
//		            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
//		        }
		System.out.println(loginRequest.getEmail());
	    String token = userService.verify(loginRequest);
	    if (!"fail".equals(token)) {
	        // Fetch the user's role from the database
	        User user = userRepository.findByEmail(loginRequest.getEmail())
	                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail()));
	        String role = user.getRole();
	        String id = String.valueOf(user.getId());
	        // Return the token and role in the response
	        Map<String, String> response = new HashMap<>();
	        response.put("token", token);
	        response.put("role", role);
	        response.put("id", id); 
	        return new ResponseEntity<>(response,HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerperson(@RequestBody User user) {
		if(user.getRole().equalsIgnoreCase("ADMIN")) {
			return new ResponseEntity<>("Change the role. You can't signup using role ADMIN!!", HttpStatus.BAD_REQUEST);
		}
		if (userService.usernameExists(user.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		if (userService.emailExists(user.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}
		userService.saveUser(user);
		return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);

	}
	@PostMapping("/refresh-token")
	public ResponseEntity<String> refreshToken(@RequestBody String expiredToken) {
		try {
	    if (jwtService.isTokenExpired(expiredToken)) {
	        String username = jwtService.extractUserName(expiredToken);
	        String newToken = jwtService.generateToken(username);
	        return ResponseEntity.ok(newToken);
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is not expired");
	    }
	} catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error refreshing token");
    }
	}
}
