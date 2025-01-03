package com.cts.cda.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cda.entity.User;
import com.cts.cda.models.CourseModel;
import com.cts.cda.models.DepartmentModel;
import com.cts.cda.models.UserModel;
import com.cts.cda.service.CourseService;
import com.cts.cda.service.DepartmentService;
import com.cts.cda.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private DepartmentService departmentService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/Allusers")
	@ResponseBody
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllUsers() {
		logger.info("Fetching all Students.");
		List<UserModel> userList = userService.getAllUserModel();
		logger.info("Fetched {} users.", userList.size());
		return ResponseEntity.ok(userList);
	}

	@GetMapping("/users/courses")
	@ResponseBody
	public ResponseEntity<List<CourseModel>> getAllCourses() {
		try {
			logger.info("The course available in the KBSR college are : ");
			List<CourseModel> courses = courseService.getAllCourses();
			logger.info("Total {} courses are available!!", courses.size());
			return ResponseEntity.ok(courses);
		} catch (Exception e) {
			logger.error("Error fetching courses: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/users/departments")
	@ResponseBody
	public ResponseEntity<List<DepartmentModel>> getAllDepartments() {
		try {
			logger.info("The departments available in the KBSR college are : ");
			List<DepartmentModel> departments = departmentService.getAllDepartments();
			logger.info("Total {} departments are available!!", departments.size());
			return ResponseEntity.ok(departments);
		} catch (Exception e) {
			logger.error("Error fetching departments: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
