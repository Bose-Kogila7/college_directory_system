package com.cts.cda.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cts.cda.entity.User;
import com.cts.cda.models.SignupModel;
import com.cts.cda.models.UserModel;





public interface UserService {
	List<User> getAllUsers();
	User saveUser(User user);
	User getUserById(Long id);
	User updateUser(User user);
//	User getUserByemail(String email);
	void deleteUserById(Long id);
	String verify(User user);
	List<UserModel> getAllUserModel();
	boolean usernameExists(String username);
	boolean emailExists(String email);
	User findByEmail(String email);
}
