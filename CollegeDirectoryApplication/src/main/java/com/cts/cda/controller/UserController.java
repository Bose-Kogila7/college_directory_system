package com.cts.cda.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cda.entity.User;
import com.cts.cda.models.CourseModel;
import com.cts.cda.models.DepartmentModel;
import com.cts.cda.models.IdModel;
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
	@GetMapping("/users/profile")
    public ResponseEntity<User> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }
	@DeleteMapping("users/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody IdModel dto) {
        try {
            Optional<User> user = userService.findById(dto.getId());
            if (user.isPresent()) {
                userService.deleteUserById(dto.getId());
                return ResponseEntity.ok("User deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User ID not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }
}
