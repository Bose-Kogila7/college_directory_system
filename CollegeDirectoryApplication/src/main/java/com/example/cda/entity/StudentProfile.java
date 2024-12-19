package com.example.cda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class StudentProfile {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String photo;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Size(max = 10, message = "year length should not exceed 10 characters")
    private String year;
    

	public StudentProfile() {
		super();
	}

	public StudentProfile(Long userId, User user, String photo, Department department, String year) {
		super();
		this.userId = userId;
		this.user = user;
		this.photo = photo;
		this.department = department;
		this.year = year;
	}

	


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
}
