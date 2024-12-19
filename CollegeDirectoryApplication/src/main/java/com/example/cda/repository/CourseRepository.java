package com.example.cda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cda.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{

}
