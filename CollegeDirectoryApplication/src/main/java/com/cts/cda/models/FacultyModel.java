package com.cts.cda.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class FacultyModel {
	
		private Long id;
	  	private Long userId;
	    private String photo;
	    @NotBlank(message = "Name must not be blank")
	    private String name;
	    private String password;
		@NotBlank(message = "Email must not be blank") 
	    @Email(message = "Email should be valid")
	    private String email;
	    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Phone number should be valid")
	    private String phone;
	    private Long departmentId;
	    private String departmentName;
	    @NotBlank(message = "Office hours must not be blank")
	    private String officeHours;

	    public FacultyModel(Long id,Long userId, String photo, String name, String email, String phone,Long departmentId , String departmentName,String officeHours) {
	        this.id=id;
	        this.userId = userId;
	        this.photo = photo;
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	        this.departmentId=departmentId;
	        this.departmentName = departmentName;
	        this.officeHours=officeHours;
	    }
}
