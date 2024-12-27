package com.cts.cda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.cda.controller.AdministratorController;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.models.StudentModel;
import com.cts.cda.repository.FacultyProfileRespository;
import com.cts.cda.repository.StudentProfileRepository;
import com.cts.cda.service.FacultyProfileService;
import com.cts.cda.service.StudentProfileService;
import com.cts.cda.service.impl.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class CollegeDirectoryApplicationTests {

	@Autowired 
	private MockMvc mockMvc; 
	@InjectMocks 
	private FacultyProfileServiceImpl facultyProfileServiceimpl; 
	@InjectMocks 
	private StudentServiceImpl studentProfileService; 
	@Mock 
	private FacultyProfileRespository facultyProfileRepository; 
	@Mock 
	private StudentProfileRepository studentProfileRepository;
	@Mock
	private FacultyProfileService facultyProfileService;
	
	@Test
	void contextLoads() {
	}
//	@BeforeEach 
//	public void setUp() 
//	{ 
//		MockitoAnnotations.openMocks(this); 
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); 
//		studentModel = new StudentModel(1L, 1L, "photo1", 1L, "year1", "username1", "student1", "email1@example.com", "+1234567890"); 
//		facultyModel = new FacultyModel(1L, 1L, "photo1", "faculty1", "email1@example.com", "+1234567890", 1L, "department1", "9am-5pm"); 
//	} 
//	@Test 
//	@WithMockUser(roles = "student") 
//	public void testSaveStudent() throws Exception 
//	{ 
//		doNothing().when(studentProfileService).saveStudentProfile(any(StudentModel.class));
//		String studentJson = "{\"photo\":\"photo1\",\"departmentId\":1,\"year\":\"year1\",\"userName\":\"username1\",\"name\":\"student1\",\"email\":\"email1@example.com\",\"phone\":\"1234567890\",\"role\":\"STUDENT\",\"password\":\"password1\"}";
//		MvcResult result = mockMvc.perform(post("/api/admin/add-Student") 
//				.contentType(MediaType.APPLICATION_JSON) 
//				.content(studentJson)) 
//				.andReturn(); 
//		System.out.println("Status: " + result.getResponse().getStatus()); 
//		System.out.println("Response: " + result.getResponse().getContentAsString());
//		assertEquals(200, result.getResponse().getStatus()); 
//		assertEquals("Saved Sucessfully", result.getResponse().getContentAsString());
//		verify(studentProfileService, times(1)).saveStudentProfile(any(StudentModel.class)); 
////		assertEquals("student1", studentModel.getName()); 
//	}
//	@Test
//	@WithMockUser(roles = "faculty")
//	public void testSaveFaculty() throws Exception {
//	    doNothing().when(facultyProfileService).saveFacultyProfile(any(FacultyModel.class));
//
//	    String facultyJson = "{\"photo\":\"photo1\",\"name\":\"faculty1\",\"email\":\"faculty1@example.com\",\"phone\":\"1234567890\",\"departmentId\":1,\"departmentName\":\"department1\",\"officeHours\":\"9am-5pm\"}";
//
//	    MvcResult result = mockMvc.perform(post("/api/admin/add-Faculty")
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .content(facultyJson))
//	            .andReturn();
//
//	    System.out.println("Status: " + result.getResponse().getStatus());
//	    System.out.println("Response: " + result.getResponse().getContentAsString());
//
//	    assertEquals(200, result.getResponse().getStatus());
//	    assertEquals("Saved Sucessfully", result.getResponse().getContentAsString());
//
//	    verify(facultyProfileService, times(1)).saveFacultyProfile(any(FacultyModel.class));
//	}
	@Test
	//@WithMockUser
	public void testDeleteFaculty() throws Exception {
	    long facultyId = 1L;

	    // Mock the service call to do nothing
	    doNothing().when(facultyProfileService).deleteFacultyProfileById(facultyId);

	    MvcResult result = mockMvc.perform(delete("/api/admin/deleteFaculty/{id}", facultyId))
	            .andExpect(status().isOk())
	            .andReturn();

	    int status = result.getResponse().getStatus();
	    String response = result.getResponse().getContentAsString();

	    System.out.println("Status: " + status);
	    System.out.println("Response: " + response);

	    assertEquals(HttpStatus.OK.value(), status);
	    assertEquals("Faculty deleted successfully.", response);

	    verify(facultyProfileService, times(1)).deleteFacultyProfileById(facultyId);
	}


}
