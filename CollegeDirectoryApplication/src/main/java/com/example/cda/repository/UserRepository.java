package com.example.cda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.cda.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);
}
