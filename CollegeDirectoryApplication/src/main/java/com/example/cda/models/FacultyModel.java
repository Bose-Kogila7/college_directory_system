package com.example.cda.models;

public class FacultyModel {
	
		private Long id;
	  	private Long userId;
	    private String photo;
	    private String name;
	    private String email;
	    private String phone;
	    private Long departmentId;
	    private String departmentName;
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
