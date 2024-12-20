package com.cts.cda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.cda.entity.User;
import com.cts.cda.repository.UserRepository;
import com.cts.cda.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
//
//	public UserServiceImpl(UserRepository userRepository) {
//		super();
//		this.userRepository = userRepository;
//	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

//	@Override
//	public User getUserByemail(String email) {
//		return userRepository.findByEmail(email);
//	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	

	
}
