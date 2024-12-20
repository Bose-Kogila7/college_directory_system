package com.cts.cda.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.cda.models.CourseModel;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.models.StudentCourseModel;
import com.cts.cda.repository.EnrollmentRepository;
import com.cts.cda.service.EnrollmentService;

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
