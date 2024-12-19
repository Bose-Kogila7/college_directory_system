package com.example.cda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cda.entity.Enrollment;
import com.example.cda.models.CourseModel;
import com.example.cda.models.FacultyModel;
import com.example.cda.models.StudentCourseModel;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

	 @Query("SELECT new com.example.cda.models.FacultyModel(f.id,f.user.id, f.photo, u.name, u.email, u.phone,d.id, d.name,f.officeHours) " +
	           "FROM Enrollment e " +
	           "JOIN e.course c " +
	           "JOIN c.faculty f " +
	           "JOIN f.user u " +
	           "JOIN f.department d " +
	           "WHERE e.student.user.id = :studentId")
	    List<FacultyModel> findAssignedFacultyByStudentId(@Param("studentId") Long studentId);
	 
	 @Query("SELECT new com.example.cda.models.StudentCourseModel(sp.user.id, sp.user.name, sp.user.email, sp.user.phone, c.title, c.description) " +
		       "FROM Enrollment e " +
		       "JOIN e.student sp " +
		       "JOIN sp.user su " +
		       "JOIN e.course c " +
		       "JOIN c.faculty f " +
		       "JOIN f.user fu " +
		       "WHERE fu.id = :facultyId")
		List<StudentCourseModel> findStudentsByFacultyId(@Param("facultyId") Long facultyId);
	 
	 @Query("SELECT new com.example.cda.models.CourseModel(" +
		        "c.title, " +
		        "c.department.id, " +
		        "f.user.id, " +
		        "f.user.name) " +
		        "FROM Enrollment e " +
		        "JOIN e.course c " +
		        "JOIN c.department d " +
		        "JOIN c.faculty f " +
		        "WHERE e.student.user.id = :studentId")
		List<CourseModel> findCoursesByStudentId(@Param("studentId") Long studentId);
	
}
