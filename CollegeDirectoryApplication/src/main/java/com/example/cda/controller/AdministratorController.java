package com.example.cda.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.cda.service.*;
import com.example.cda.entity.*;
import com.example.cda.models.FacultyModel;
import com.example.cda.models.StudentModel;

@RestController
@RequestMapping("/api")
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
	
	
//	@GetMapping("/Login")
//	public String Home()
//	{
//		return "Login";
//	}
//	@PostMapping("/Login")
//	@ResponseBody
//	public ResponseEntity<LoginResponse>  Home(@RequestParam String email,@RequestParam String password,@RequestParam String role)
//	{
//		
//		User  user = userService.getUserByemail(email);
//		System.out.println(email+"-----"+password+" "+role+" "+user.getRole());
//		if(user!=null && user.getPassword().compareTo(password)==0 && user.getRole().compareTo(role)==0)
//		{
//			String redirectUrl;
//			Object profile;
//            switch (role) {
//                case "student":
//                	profile = studentProfileService.getStudentProfileByuserId(user.getId());
//                    redirectUrl = "/studentDashboard";
//                    break;
//                case "faculty":
//                	profile = facultyProfileService.getFacultyProfileByuserId(user.getId());
//                    redirectUrl = "/facultyDashboard";
//                    break;
//                case "admin":
//                	profile = administratorService.getAdministratorProfileByuserId(user.getId());
//                    redirectUrl = "/administratorDashboard";
//                    break;
//                default:
//                	return ResponseEntity.badRequest().body(null);            }
//            System.out.println(email+" "+password+" "+role+"--------"+user.getRole()+" "+redirectUrl+" "+profile);
//            LoginResponse response = new LoginResponse(user, profile);
//            return ResponseEntity.ok(response);
//        }
//		return ResponseEntity.badRequest().body(null);	
//	}
	
	private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);
	
	@GetMapping("/admin/getAllStudent")
	@ResponseBody
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
	@PreAuthorize("hasRole('student')")
	public ResponseEntity<String>  saveStudent(@RequestBody StudentModel studentModel)
	{
		logger.info("Saving Student {}",studentModel.getName());
		//System.out.println(studentModel.getEmail()+" "+studentModel.getName()+" "+studentModel.getPassword()+" "+studentModel.getUserName()+" "+studentModel.getYear());
		studentProfileService.saveStudentProfile(studentModel);
		return ResponseEntity.ok("Saved Sucessfully");
	}
	
	@PostMapping("/admin/add-Faculty")
	@PreAuthorize("hasRole('faculty')")
	public ResponseEntity<String>  saveFaculty(@RequestBody FacultyModel facultyModel)
	{
		logger.info("Saving Faculty {}",facultyModel.getName());
		facultyProfileService.saveFacultyProfile(facultyModel);
		return ResponseEntity.ok("Saved Sucessfully");
	}
	@DeleteMapping("/admin/deleteFaculty/{id}")
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
	 public ResponseEntity<String> updateFaculty(@PathVariable String Id,@RequestBody FacultyModel facultyModel)
	 {
		 logger.info("Updating Faculty Id{}",Id);
		 FacultyProfile  fp = facultyProfileService.updateFacultyProfile(Long.parseLong(Id), facultyModel);
		 return ResponseEntity.ok("Updated Faculty successfully");
	 }
}
