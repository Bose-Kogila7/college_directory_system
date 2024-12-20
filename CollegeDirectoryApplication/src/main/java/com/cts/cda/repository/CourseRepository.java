package com.cts.cda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.cda.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{

}
