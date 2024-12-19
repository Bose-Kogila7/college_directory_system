package com.example.cda.service;

import java.util.List;

import com.example.cda.entity.User;





public interface UserService {
	List<User> getAllUsers();
	User saveUser(User user);
	User getUserById(Long id);
	User updateUser(User user);
//	User getUserByemail(String email);
	void deleteUserById(Long id);
}
