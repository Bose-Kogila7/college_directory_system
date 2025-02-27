package com.cts.cda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.cda.entity.StudentProfile;
import com.cts.cda.models.StudentModel;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
	@Query("SELECT new com.cts.cda.models.StudentModel(sp.id,u.id, sp.photo, sp.department.id, sp.year,u.username, u.name as name, u.email as email, u.phone as phone) " +
            "FROM StudentProfile sp " +
            "JOIN sp.user u " +
            "WHERE sp.user.id = :userId")
	StudentModel findUserIdPhotoYearDepartmentByUserId(Long userId);
	
    @Query("SELECT new com.cts.cda.models.StudentModel(sp.id,u.id, sp.photo, sp.department.id, sp.year,u.username, u.name as name, u.email as email, u.phone as phone) " +
            "FROM StudentProfile sp " +
            "JOIN sp.user u " +
            "JOIN sp.department d " +
            "WHERE (CAST(d.id AS string) LIKE %:keyword% OR " +
            "u.name LIKE %:keyword% OR " +
            "sp.year LIKE %:keyword%)")
	 List<StudentModel> findStudentBYKey(@Param("keyword") String keyword);
    
    
    @Query("SELECT new com.cts.cda.models.StudentModel(sp.id,u.id, sp.photo, sp.department.id, sp.year,u.username, u.name as name, u.email as email, u.phone as phone) " +
            "FROM StudentProfile sp " +
            "JOIN sp.user u " +
            "JOIN sp.department d " )
	 List<StudentModel> findAllStudentModel();
   
    @Query("SELECT new com.cts.cda.models.StudentModel(s.id, s.user.id, s.photo, u.name, u.email, u.phone, d.id, d.name, s.year) " +
               "FROM StudentProfile s " +
               "JOIN s.user u " +
               "JOIN s.department d " +
               "WHERE s.user.id = :userId")
      StudentModel findStudentByUserId(@Param("userId") Long userId);

    
    
	
	

}
