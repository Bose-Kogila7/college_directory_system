package com.cts.cda.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cda.entity.User;
import com.cts.cda.models.UserModel;
import com.cts.cda.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/users/getAllUsers")
	@ResponseBody
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllUsers() {
		logger.info("Fetching all Students.");
	    List<User> userList = userService.getAllUsers();
	    logger.info("Fetched {} users.",userList.size());
	    return ResponseEntity.ok(userList);
	}
}
