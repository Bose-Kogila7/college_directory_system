package com.example.cda.controller;

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

//import com.example.cda.entity.Person;
import com.example.cda.entity.Role;
import com.example.cda.entity.User;
import com.example.cda.models.LoginModel;
import com.example.cda.models.SignupModel;
//import com.example.cda.repository.PersonRepository;
import com.example.cda.repository.RoleRepository;
import com.example.cda.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
//	 @Autowired
//	    private AuthenticationManager authenticationManager;
//
//	    @Autowired
//	    private PersonRepository personRepository;

//	    @Autowired
//	    private RoleRepository roleRepository;
//
//	    @Autowired
//	    private PasswordEncoder passwordEncoder;
	
		@Autowired
		UserService userService;

//	    @PostMapping("/signin")
//	    public ResponseEntity<String> authenticateperson(@RequestBody LoginModel loginmodel){
//	    	try {
//	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//	                loginmodel.getUsernameOrEmail(), loginmodel.getPassword()));
//
//	        SecurityContextHolder.getContext().setAuthentication(authentication);
//	        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
//	    	}catch(AuthenticationException e) {
//	    		return new ResponseEntity<>("Invalid credentials!!",HttpStatus.UNAUTHORIZED);
//	    	}
//	    	
//	    }

	    @PostMapping("/signup")
	    public ResponseEntity<?> registerperson(@RequestBody User user){
//
//	        // add check for personname exists in a DB
//	        if(personRepository.existsByUsername(signUpModel.getUsername())){
//	            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
//	        }
//
//	        // add check for email exists in DB
//	        if(personRepository.existsByEmail(signUpModel.getEmail())){
//	            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
//	        }
//
//	        // create person object
//	        Person person = new Person();
//	        person.setName(signUpModel.getName());
//	        person.setUsername(signUpModel.getUsername());
//	        person.setEmail(signUpModel.getEmail());
//	        person.setPassword(passwordEncoder.encode(signUpModel.getPassword()));
//
//	        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
//	        person.setRoles(Collections.singleton(roles));
//
//	        personRepository.save(person);
//
	    	userService.saveUser(user);
	        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	    }
	    
	 
}

