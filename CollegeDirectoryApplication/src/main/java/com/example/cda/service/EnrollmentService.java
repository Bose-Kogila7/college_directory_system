package com.example.cda.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.cda.models.CourseModel;
import com.example.cda.models.FacultyModel;
import com.example.cda.models.StudentCourseModel;

public interface EnrollmentService {
	List<FacultyModel> getAssignedFacultyByStudentId(Long studentId);
	List<StudentCourseModel> getStudentsByFacultyId(Long facultyId);
	List<CourseModel> getCourseByStudentId(Long studentId);
}
