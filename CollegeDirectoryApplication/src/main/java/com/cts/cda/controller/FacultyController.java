package com.cts.cda.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cda.entity.FacultyProfile;
import com.cts.cda.entity.StudentProfile;
import com.cts.cda.entity.User;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.models.StudentCourseModel;
import com.cts.cda.service.EnrollmentService;
import com.cts.cda.service.FacultyProfileService;
import com.cts.cda.service.StudentProfileService;
import com.cts.cda.service.UserService;

@RestController
@RequestMapping("/api")
public class FacultyController {
	private UserService userService;
	private StudentProfileService studentProfileService;
	private FacultyProfileService facultyProfileService;
	private EnrollmentService enrollmentService;
	public FacultyController(UserService userService, StudentProfileService studentProfileService,
			FacultyProfileService facultyProfileService,EnrollmentService enrollmentService) {
		super();
		this.userService = userService;
		this.studentProfileService = studentProfileService;
		this.facultyProfileService = facultyProfileService;
		this.enrollmentService=enrollmentService;
	}
	private static final Logger logger = LoggerFactory.getLogger(FacultyController.class);
	 @GetMapping("/faculty/profile")
	    public ResponseEntity<User> getFacultyProfile() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();
	        User user = userService.findByEmail(email);
	        return ResponseEntity.ok(user);
	    }
	
	@GetMapping("/faculty/student-by-course/{facultyId}")
	@PreAuthorize("hasAuthority('faculty')")
	@ResponseBody
	public ResponseEntity<?> getStudentList(@PathVariable Long facultyId)
	{
		Optional<FacultyProfile> facultyProfile = facultyProfileService.findById(facultyId);
        if (!facultyProfile.isPresent()) {
        	logger.info("You are not a registered faculty. Contact your administrator.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("You are not a registered faculty. Contact your administrator.");
        }
		System.out.println("Hello "+facultyId);
		List<StudentCourseModel>  studentList = enrollmentService.getStudentsByFacultyId(facultyId);
		return ResponseEntity.ok(studentList);
	}
	@PutMapping("/faculty/update/{Id}")
	@PreAuthorize("hasAuthority('faculty')")
    public ResponseEntity<String> updateFaculty(@PathVariable String Id, @RequestBody FacultyModel facultyModel) {
		try {
			logger.info("Checking if Faculty with ID {} exists...", Id);
			Optional<FacultyProfile> facultyProfile = facultyProfileService.findById(Long.parseLong(Id));
			if (facultyProfile.isPresent()) {
				logger.info("Updating Faculty with ID {}", Id);
				FacultyProfile fp = facultyProfileService.updateFacultyProfile(Long.parseLong(Id), facultyModel);
				return ResponseEntity.ok("Updated Faculty successfully.");
			} else {
				logger.warn("Faculty with ID {} not found.", Id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Faculty ID not found.");
			}
		} catch (Exception e) {
			logger.error("Error updating Faculty ID {}: {}", Id, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating faculty: " + e.getMessage());
		}
	}
	@GetMapping("/faculty/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return facultyProfileService.getImage(id);
    }
	
}
