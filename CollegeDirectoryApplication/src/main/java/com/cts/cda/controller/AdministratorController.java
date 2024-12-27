package com.cts.cda.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cts.cda.entity.*;
import com.cts.cda.models.FacultyModel;
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
	
	@GetMapping("/admin/getAllStudent")
	//@ResponseBody
	public ResponseEntity<?> getAllStudents() {
		logger.info("Fetching all Students.");
	    List<StudentModel> studentList = studentProfileService.getAllStudentModel();
	    logger.info("Fetched {} students.",studentList.size());
	    return ResponseEntity.ok(studentList);
	}
	
	@GetMapping("/admin/getAllFaculty")
	@ResponseBody
	public ResponseEntity<?> getAllFaculty()
	{
		logger.info("Fetching all Faculty.");
		List<FacultyModel> facultyList = facultyProfileService.getAllFacultyModel();
		logger.info("Fetched {} faculty.",facultyList.size());
        return ResponseEntity.ok(facultyList);
	}
	
	@PostMapping("/admin/add-Student")
	@PreAuthorize("hasAuthority('student')")
	public ResponseEntity<String>  saveStudent(@Valid @RequestBody StudentModel studentModel)
	{
		try {
		logger.info("Saving Student {}",studentModel.getName());
		//System.out.println(studentModel.getEmail()+" "+studentModel.getName()+" "+studentModel.getPassword()+" "+studentModel.getUserName()+" "+studentModel.getYear());
		studentProfileService.saveStudentProfile(studentModel);
		return ResponseEntity.ok("Saved Sucessfully");
		}catch(Exception e) {
			logger.error("Error saving student: {}", e.getMessage()); 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving student");
		}
	}
	
	@PostMapping("/admin/add-Faculty")
	@PreAuthorize("hasAuthority('faculty')")
	public ResponseEntity<String>  saveFaculty(@Valid @RequestBody FacultyModel facultyModel)
	{
		try {
		logger.info("Saving Faculty {}",facultyModel.getName());
		facultyProfileService.saveFacultyProfile(facultyModel);
		return ResponseEntity.ok("Saved Sucessfully");
		}catch(Exception e) {
			logger.error("Error saving faculty: {}", e.getMessage()); 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving faculty");
		}
	}
	@DeleteMapping("/admin/deleteFaculty/{id}")
	@PreAuthorize("hasAuthority('faculty')")
    public ResponseEntity<String> deleteFaculty(@PathVariable String  id) {
        try {
        	logger.info("Deleting Faculty...");
        	facultyProfileService.deleteFacultyProfileById(Long.parseLong(id));
            return ResponseEntity.ok("Faculty deleted successfully.");
        } catch (Exception e) {
        	logger.error("Error deleting faculty id{}",id);
            return ResponseEntity.status(500).body("Error deleting faculty: " + e.getMessage());
        }
    }
	
	 @DeleteMapping("/admin/deleteStudent/{id}")
	    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
	        try {
	        	logger.info("Deleting Student...");
	        	studentProfileService.deleteStudentProfileById(Long.parseLong(id));
	            return ResponseEntity.ok("Student deleted successfully.");
	        } catch (Exception e) {
	        	logger.error("Error deleting Student id{}",id);
	            return ResponseEntity.status(500).body("Error deleting student: " + e.getMessage());
	        }
	    }
	 @PutMapping("/admin/update/faculty/{Id}")
	 @PreAuthorize("hasAuthority('faculty')")
	 public ResponseEntity<String> updateFaculty(@PathVariable String Id,@RequestBody FacultyModel facultyModel)
	 {
		 logger.info("Updating Faculty Id{}",Id);
		 FacultyProfile  fp = facultyProfileService.updateFacultyProfile(Long.parseLong(Id), facultyModel);
		 return ResponseEntity.ok("Updated Faculty successfully");
	 }
}
