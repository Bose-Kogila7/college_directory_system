package com.example.cda.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cda.models.CourseModel;
import com.example.cda.models.FacultyModel;
import com.example.cda.models.StudentCourseModel;
import com.example.cda.repository.EnrollmentRepository;
import com.example.cda.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	private EnrollmentRepository enrollmentRepository;
	
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
		super();
		this.enrollmentRepository = enrollmentRepository;
	}

	@Override
	public List<FacultyModel> getAssignedFacultyByStudentId(Long studentId) {
		return enrollmentRepository.findAssignedFacultyByStudentId(studentId);
	}

	@Override
	public List<StudentCourseModel> getStudentsByFacultyId(Long facultyId) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findStudentsByFacultyId(facultyId);
	}

	@Override
	public List<CourseModel> getCourseByStudentId(Long studentId) {
		// TODO Auto-generated method stub
		return enrollmentRepository.findCoursesByStudentId(studentId);
	}

}
