package com.example.cda.models;

public class StudentModel {
	
	private Long id;
	private Long userId;
    private String photo;
    private Long departmentId;
    private String year;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String password;
    private String userName;
    public StudentModel(Long id,Long userId, String photo, Long departmentId, String year,String userName, String name, String email, String phone) {
        this.id=id;
    	this.userId = userId;
        this.photo = photo;
        this.departmentId = departmentId;
        this.year = year;
        this.userName=userName;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRole() {
		return "student";
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

}
