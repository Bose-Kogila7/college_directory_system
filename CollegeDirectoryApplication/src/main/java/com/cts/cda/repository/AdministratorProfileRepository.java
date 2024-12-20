package com.cts.cda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.cda.entity.AdministratorProfile;
import com.cts.cda.models.AdministratorModel;

public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long>{
	 @Query("SELECT new com.cts.cda.models.AdministratorModel(f.id,f.user.id, f.photo, u.name, u.email, u.phone, d.name) " +
	            "FROM AdministratorProfile f " +
	            "JOIN f.user u " +
	            "JOIN f.department d " +
	            "WHERE f.user.id = :userId")
	     AdministratorModel findAdministratorByUserId(@Param("userId") Long userId);
}
