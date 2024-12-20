package com.cts.cda.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "users") 
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String username;
	private String password;
	private String role;
	private String name;
	private String email;
	private String phone;
	
	
	public User() {
		super();
	}
	
	public User(String username, String role, String name, String email, String phone) {
		super();
		this.username = username;
		this.password = name.substring(0)+""+phone.substring(5);
		this.role = role;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public User(Long id, String username, String role, String name, String email, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.password = name.substring(0)+""+phone.substring(5);
		this.role = role;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	/* Getters and Setters */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	
	
}
