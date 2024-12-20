package com.cts.cda.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.cda.models.CourseModel;
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
			FacultyProfileService facultyProfileService,EnrollmentService enrollmentService) {
		super();
		this.userService = userService;
		this.studentProfileService = studentProfileService;
		this.facultyProfileService = facultyProfileService;
		this.enrollmentService=enrollmentService;
	}

	@GetMapping("/student/search/{Key}")
	@ResponseBody
	public ResponseEntity<?> searchStudents(@PathVariable String Key)
	{
		System.out.println("Hii");
		List<StudentModel> studentsList  =  studentProfileService.getStudentByKey(Key);
		return ResponseEntity.ok(studentsList);
	}
	
	@GetMapping("/student/faculty-advised/{Id}")
	@ResponseBody
	public ResponseEntity<?> getFacultyAdvisedList(@PathVariable String Id)
	{	
		System.out.println("Hello");
		List<FacultyModel> facultyList = enrollmentService.getAssignedFacultyByStudentId(Long.parseLong(Id));
		return ResponseEntity.ok(facultyList);
	}
	
	@GetMapping("/student/course/{Id}")
	@ResponseBody
	public ResponseEntity<?> getCourseList(@PathVariable String Id)
	{
		System.out.println("Hey");
		List<CourseModel> courseList = enrollmentService.getCourseByStudentId(Long.parseLong(Id));
		return ResponseEntity.ok(courseList);
	}
	

   
}
