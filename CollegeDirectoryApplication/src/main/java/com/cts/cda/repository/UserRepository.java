package com.cts.cda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.cda.entity.User;
import com.cts.cda.models.UserModel;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);

	@Query("SELECT new com.cts.cda.models.UserModel(u.id, u.username,null, u.role, u.name, u.email, u.phone) "
			+ "FROM User u")
	List<UserModel> findAllUserModels();
	Boolean existsByUsername(String username); 
	Boolean existsByEmail(String email);
}
