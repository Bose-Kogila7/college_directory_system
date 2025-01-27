package com.cts.cda.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cts.cda.entity.*;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.models.IdModel;
import com.cts.cda.models.StudentModel;
import com.cts.cda.service.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class AdministratorController {

	private UserService userService;
	private StudentProfileService studentProfileService;
	private FacultyProfileService facultyProfileService;
	private AdministratorProfileService administratorService;

	public AdministratorController(UserService userService, StudentProfileService studentProfileService,
			FacultyProfileService facultyProfileService, AdministratorProfileService administratorService) {
		super();
		this.userService = userService;
		this.studentProfileService = studentProfileService;
		this.facultyProfileService = facultyProfileService;
		this.administratorService = administratorService;
	}

	private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
	
	 @GetMapping("/admin/profile")
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public ResponseEntity<User> getAdminProfile() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();
	        User user = userService.findByEmail(email);
	        return ResponseEntity.ok(user);
	    }
	 
	@GetMapping("/admin/getAllStudent")
	// @ResponseBody
	public ResponseEntity<?> getAllStudents() {
		logger.info("Fetching all Students.");
		List<StudentModel> studentList = studentProfileService.getAllStudentModel();
		logger.info("Fetched {} students.", studentList.size());
		return ResponseEntity.ok(studentList);
	}

	@GetMapping("/admin/getAllFaculty")
	@ResponseBody
	public ResponseEntity<?> getAllFaculty() {
		logger.info("Fetching all Faculty.");
		List<FacultyModel> facultyList = facultyProfileService.getAllFacultyModel();
		logger.info("Fetched {} faculty.", facultyList.size());
		return ResponseEntity.ok(facultyList);
	}

	@PostMapping("/admin/add-Student")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> saveStudent(@Valid @RequestBody StudentModel studentModel) {
		try {
			if (userService.emailExists(studentModel.getEmail())) {
				return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
			}
			logger.info("Saving Student {}", studentModel.getName());
			studentProfileService.saveStudentProfile(studentModel);
			return ResponseEntity.ok("Saved Sucessfully");
		} catch (Exception e) {
			logger.error("Error saving student: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving student");
		}
	}

	@PostMapping("/admin/add-Faculty")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> saveFaculty(@Valid @RequestBody FacultyModel facultyModel) {
		try {
			if (userService.emailExists(facultyModel.getEmail())) {
				return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
			}
			logger.info("Saving Faculty {}", facultyModel.getName());
			facultyProfileService.saveFacultyProfile(facultyModel);
			return ResponseEntity.ok("Saved Sucessfully");
		} catch (Exception e) {
			logger.error("Error saving faculty: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving faculty");
		}
	}
	@DeleteMapping("/admin/deleteFaculty")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteFaculty(@RequestBody IdModel dto) {
		try {
			logger.info("Checking if Faculty with ID {} exists...", dto.getId());
			Optional<FacultyProfile> facultyProfile = facultyProfileService.findById(dto.getId());
			if (facultyProfile.isPresent()) {
				logger.info("Deleting Faculty with ID {}", dto.getId());
				facultyProfileService.deleteFacultyProfileById(dto.getId());
				return ResponseEntity.ok("Faculty deleted successfully.");
			} else {
				logger.warn("Faculty with ID {} not found.", dto.getId());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty ID not found.");
			}
		} catch (Exception e) {
			logger.error("Error deleting faculty id {}: {}", dto.getId(), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting faculty: " + e.getMessage());
		}
	}

	@DeleteMapping("/admin/deleteStudent")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteStudent(@RequestBody IdModel dto) {
		try {
			logger.info("Checking if Student with ID {} exists...", dto.getId());
			Optional<StudentProfile> studentProfile = studentProfileService.findById(dto.getId());
			if (studentProfile.isPresent()) {
				logger.info("Deleting Student with ID {}", dto.getId());
				studentProfileService.deleteStudentProfileById(dto.getId());
				return ResponseEntity.ok("Student deleted successfully.");
			} else {
				logger.warn("Student with ID {} not found.", dto.getId());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ID not found.");
			}
		} catch (Exception e) {
			logger.error("Error deleting Student id {}: {}", dto.getId(), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting student: " + e.getMessage());
		}
	}

	@PutMapping("/faculty/update")
	@PreAuthorize("hasAnyAuthority('faculty', 'ADMIN')")
	public ResponseEntity<String> updateFaculty(@RequestBody FacultyModel facultyModel) {
		try {
			logger.info("Checking if Faculty with ID {} exists...", facultyModel.getId());
			Optional<FacultyProfile> facultyProfile = facultyProfileService.findById(facultyModel.getId());
			if (facultyProfile.isPresent()) {
				logger.info("Updating Faculty with ID {}", facultyModel.getId());
				FacultyProfile fp = facultyProfileService.updateFacultyProfile(facultyModel.getId(), facultyModel);
				return ResponseEntity.ok("Updated Faculty successfully.");
			} else {
				logger.warn("Faculty with ID {} not found.", facultyModel.getId());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty ID not found.");
			}
		} catch (Exception e) {
			logger.error("Error updating Faculty ID {}: {}", facultyModel.getId(), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating faculty: " + e.getMessage());
		}
	}
	@PutMapping("/student/update")
	@PreAuthorize("hasAnyAuthority('student', 'ADMIN')")
    public ResponseEntity<String> updateStudent(@RequestBody StudentModel studentModel) {
		try {
			logger.info("Checking if Student with ID {} exists...", studentModel.getId());
			Optional<StudentProfile> studentProfile = studentProfileService.findById(studentModel.getId());
			if (studentProfile.isPresent()) {
				logger.info("Updating Student with ID {}", studentModel.getId());
				StudentProfile fp = studentProfileService.updateStudentProfile(studentModel.getId(), studentModel);
				return ResponseEntity.ok("Updated Student successfully.");
			} else {
				logger.warn("Student with ID {} not found.", studentModel.getId());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ID not found.");
			}
		} catch (Exception e) {
			logger.error("Error updating Student ID {}: {}", studentModel.getId(), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating student: " + e.getMessage());
		}
	}
}
