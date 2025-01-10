package com.cts.cda.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class StudentModel {
	
	private Long id;
	private Long userId;
    private String photo;
    private Long departmentId;
    private String year;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Phone number should be valid")
    private String phone;
    private String role;
    private String password;
    @NotBlank(message = "User Name must not be blank")
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
    public StudentModel(Long id, Long userId, String photo, String name, String email, String phone, Long departmentId, String departmentName, String year) {
        this.id = id;
        this.userId = userId;
        this.photo = photo;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
        this.year = year;
    }

	public StudentModel() {
	}
	

}
