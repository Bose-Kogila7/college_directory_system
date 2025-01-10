package com.cts.cda.models;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.Data;

@Data
@EnableJpaRepositories
public class UserModel {
	
	private Long id;
	private String username;
	private String password;
	private String role;
	private String name;
	private String email;
	private String phone;
	public UserModel(Long id, String username, String password, String role, String name, String email, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
