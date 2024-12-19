package com.example.cda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cda.entity.StudentProfile;
import com.example.cda.models.StudentModel;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
	@Query("SELECT new com.example.cda.models.StudentModel(sp.id,u.id, sp.photo, sp.department.id, sp.year,u.username, u.name as name, u.email as email, u.phone as phone) " +
            "FROM StudentProfile sp " +
            "JOIN sp.user u " +
            "WHERE sp.user.id = :userId")
	StudentModel findUserIdPhotoYearDepartmentByUserId(Long userId);
	
    @Query("SELECT new com.example.cda.models.StudentModel(sp.id,u.id, sp.photo, sp.department.id, sp.year,u.username, u.name as name, u.email as email, u.phone as phone) " +
            "FROM StudentProfile sp " +
            "JOIN sp.user u " +
            "JOIN sp.department d " +
            "WHERE (CAST(d.id AS string) LIKE %:keyword% OR " +
            "u.name LIKE %:keyword% OR " +
            "sp.year LIKE %:keyword%)")
	 List<StudentModel> findStudentBYKey(@Param("keyword") String keyword);
    
    
    @Query("SELECT new com.example.cda.models.StudentModel(sp.id,u.id, sp.photo, sp.department.id, sp.year,u.username, u.name as name, u.email as email, u.phone as phone) " +
            "FROM StudentProfile sp " +
            "JOIN sp.user u " +
            "JOIN sp.department d " )
	 List<StudentModel> findAllStudentModel();
    
    
	
	

}
