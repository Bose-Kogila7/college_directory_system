package com.cts.cda.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.cts.cda.models.CourseModel;
import com.cts.cda.models.EnrollmentModel;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.models.StudentModel;
import com.cts.cda.service.EnrollmentService;
import com.cts.cda.service.FacultyProfileService;
import com.cts.cda.service.StudentProfileService;
import com.cts.cda.service.UserService;

@RestController
@RequestMapping("/api")
public class StudentControllerr {

	private UserService userService;
	private StudentProfileService studentProfileService;
	private FacultyProfileService facultyProfileService;
	private EnrollmentService enrollmentService;

	public StudentControllerr(UserService userService, StudentProfileService studentProfileService,
			FacultyProfileService facultyProfileService, EnrollmentService enrollmentService) {
		super();
		this.userService = userService;
		this.studentProfileService = studentProfileService;
		this.facultyProfileService = facultyProfileService;
		this.enrollmentService = enrollmentService;
	}

	private static final Logger logger = LoggerFactory.getLogger(StudentControllerr.class);

	@GetMapping("/student/search/{Key}")
	@ResponseBody
	public ResponseEntity<?> searchStudents(@PathVariable String Key) {
		System.out.println("Hii");
		List<StudentModel> studentsList = studentProfileService.getStudentByKey(Key);
		return ResponseEntity.ok(studentsList);
	}

	@GetMapping("/student/faculty-advised/{Id}")
	@ResponseBody
	public ResponseEntity<?> getFacultyAdvisedList(@PathVariable String Id) {
		System.out.println("Hello");
		List<FacultyModel> facultyList = enrollmentService.getAssignedFacultyByStudentId(Long.parseLong(Id));
		return ResponseEntity.ok(facultyList);
	}

	@GetMapping("/student/course/{Id}")
	@PreAuthorize("hasAuthority('student')")
	@ResponseBody
	public ResponseEntity<?> getCourseList(@PathVariable String Id) {
		System.out.println("Hey");
		List<CourseModel> courseList = enrollmentService.getCourseByStudentId(Long.parseLong(Id));
		return ResponseEntity.ok(courseList);
	}

	@PutMapping("/student/update/{Id}")
	@PreAuthorize("hasAuthority('student')")
	public ResponseEntity<String> updateStudent(@PathVariable String Id, @RequestBody StudentModel studentModel) {
		try {
			logger.info("Checking if Student with ID {} exists...", Id);
			Optional<StudentProfile> studentProfile = studentProfileService.findById(Long.parseLong(Id));
			if (studentProfile.isPresent()) {
				logger.info("Updating Student with ID {}", Id);
				StudentProfile fp = studentProfileService.updateStudentProfile(Long.parseLong(Id), studentModel);
				return ResponseEntity.ok("Updated Student successfully.");
			} else {
				logger.warn("Student with ID {} not found.", Id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student ID not found.");
			}
		} catch (Exception e) {
			logger.error("Error updating Student ID {}: {}", Id, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating student: " + e.getMessage());
		}
	}

	@PostMapping("/student/enroll")
	@PreAuthorize("hasAuthority('student')")
	public ResponseEntity<String> enrollInCourse(@RequestBody EnrollmentModel enrollmentModel) {
		try {
			enrollmentService.enrollStudentInCourse(enrollmentModel.getStudent_id(), enrollmentModel.getCourse_id());
			return ResponseEntity.ok("Student enrolled in course successfully.");
		} catch (Exception e) {
			logger.error("Error enrolling student in course: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error enrolling student in course: " + e.getMessage());
		}
	}
	@GetMapping("/enrollments") 
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<EnrollmentModel>> getAllEnrollments() {
		try {
			List<EnrollmentModel> enrollments = enrollmentService.getAllEnrollments();
			return ResponseEntity.ok(enrollments);
		} catch (Exception e) {
			logger.error("Error fetching enrollments: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
