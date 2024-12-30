package com.cts.cda.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.cda.entity.Course;
import com.cts.cda.entity.Enrollment;
import com.cts.cda.entity.StudentProfile;
import com.cts.cda.models.CourseModel;
import com.cts.cda.models.EnrollmentModel;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.models.StudentCourseModel;
import com.cts.cda.repository.CourseRepository;
import com.cts.cda.repository.EnrollmentRepository;
import com.cts.cda.repository.StudentProfileRepository;
import com.cts.cda.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;
	private final StudentProfileRepository studentProfileRepository;
	private final CourseRepository courseRepository;

	@Autowired
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
			StudentProfileRepository studentProfileRepository, CourseRepository courseRepository) {
		this.enrollmentRepository = enrollmentRepository;
		this.studentProfileRepository = studentProfileRepository;
		this.courseRepository = courseRepository;
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

	@Override
	public void enrollStudentInCourse(Long studentId, Long courseId) {
		// TODO Auto-generated method stub
		StudentProfile student = studentProfileRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found"));
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
		Enrollment enrollment = new Enrollment();
		enrollment.setStudent(student);
		enrollment.setCourse(course);
		enrollmentRepository.save(enrollment);

	}

	@Override
	public List<EnrollmentModel> getAllEnrollments() {
		// TODO Auto-generated method stub
		List<Enrollment> enrollments = enrollmentRepository.findAll();
		List<EnrollmentModel> enrollmentModels = new ArrayList<>();
		for (Enrollment enrollment : enrollments) {
			EnrollmentModel model = new EnrollmentModel(enrollment.getId(), enrollment.getStudent().getUserId(),
					enrollment.getCourse().getId());
			enrollmentModels.add(model);
		}
		return enrollmentModels;
	}

}
