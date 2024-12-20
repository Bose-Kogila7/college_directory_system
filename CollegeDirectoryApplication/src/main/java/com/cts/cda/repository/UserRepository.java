package com.cts.cda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.cda.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);
}
