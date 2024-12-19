package com.example.cda.models;

public class AdministratorModel {
		private Long id;
	 	private Long userId;
	    private String photo;
	    private String name;
	    private String email;
	    private String phone;
	    private String departmentName;
		public AdministratorModel(Long id,Long userId, String photo, String name, String email, String phone,
				String departmentName) {
			super();
			this.id=id;
			this.userId = userId;
			this.photo = photo;
			this.name = name;
			this.email = email;
			this.phone = phone;
			this.departmentName = departmentName;
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
}
