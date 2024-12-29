package com.cts.cda.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
	    public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		public String getOfficeHours() {
			return officeHours;
		}

		public void setOfficeHours(String officeHours) {
			this.officeHours = officeHours;
		}

		public Long getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
		}
		
}
