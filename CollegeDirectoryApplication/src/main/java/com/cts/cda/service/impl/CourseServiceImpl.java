package com.cts.cda.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.cda.entity.Course;
import com.cts.cda.models.CourseModel;
import com.cts.cda.repository.CourseRepository;
import com.cts.cda.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<CourseModel> getAllCourses() {
		List<Course> courses = courseRepository.findAll();
		List<CourseModel> courseModels = new ArrayList<>();
		for (Course course : courses) {
			CourseModel model = new CourseModel(
					course.getId(),
					course.getTitle(), 
					course.getDescription(),
					course.getDepartment().getId(),
					course.getFaculty().getUserId(), 
					course.getFaculty().getUser().getName()
			);
			courseModels.add(model);
		}
		return courseModels;
	}

}
