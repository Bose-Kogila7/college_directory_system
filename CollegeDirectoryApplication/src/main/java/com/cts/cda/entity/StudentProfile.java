package com.cts.cda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class StudentProfile {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
	private String password;

	@MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
    private byte[] photo;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private String year;
    

	public StudentProfile() {
		super();
	}

	public StudentProfile(Long userId, User user,byte[] photo, Department department, String year) {
		super();
		this.userId = userId;
		this.user = user;
		this.photo = photo;
		this.department = department;
		this.year = year;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
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
