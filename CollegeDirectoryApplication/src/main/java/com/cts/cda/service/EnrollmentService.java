package com.cts.cda.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.cts.cda.models.CourseModel;
import com.cts.cda.models.EnrollmentModel;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.models.StudentCourseModel;

public interface EnrollmentService {
	List<FacultyModel> getAssignedFacultyByStudentId(Long studentId);
	List<StudentCourseModel> getStudentsByFacultyId(Long facultyId);
	List<CourseModel> getCourseByStudentId(Long studentId);
	void enrollStudentInCourse(Long studentId, Long courseId);
	List<EnrollmentModel> getAllEnrollments();
	boolean isStudentEnrolledInCourse(Long studentId, Long courseId);
}
