package com.cts.cda.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
	@GetMapping("/faculty/student-by-course/{facultyId}")
	@ResponseBody
	public ResponseEntity<?> getStudentList(@PathVariable Long facultyId)
	{
		System.out.println("Hello "+facultyId);
		List<StudentCourseModel>  studentList = enrollmentService.getStudentsByFacultyId(facultyId);
		return ResponseEntity.ok(studentList);
	}
	@PostMapping("/faculty/update/{id}")
    public ResponseEntity<FacultyModel> updateFaculty(@PathVariable String id, @RequestBody FacultyModel updateDTO) {
        System.out.print(id+" "+updateDTO.getOfficeHours()+" ");
		FacultyProfile updatedFaculty = facultyProfileService.updateFacultyProfile(Long.parseLong(id),updateDTO);
		System.out.println("-----------"+updatedFaculty.getOfficeHours());
		return ResponseEntity.ok(facultyProfileService.getFacultyProfileByuserId(updatedFaculty.getUserId()));
    }
	
}