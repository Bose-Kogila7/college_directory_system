package com.example.cda.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;

    @OneToMany(mappedBy = "department")
    private List<StudentProfile> students;

    @OneToMany(mappedBy = "department")
    private List<FacultyProfile> facultyMembers;

    @OneToMany(mappedBy = "department")
    private List<AdministratorProfile> administrators;

    @OneToMany(mappedBy = "department")
    private List<Course> courses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<StudentProfile> getStudents() {
		return students;
	}

	public void setStudents(List<StudentProfile> students) {
		this.students = students;
	}

	public List<FacultyProfile> getFacultyMembers() {
		return facultyMembers;
	}

	public void setFacultyMembers(List<FacultyProfile> facultyMembers) {
		this.facultyMembers = facultyMembers;
	}

	public List<AdministratorProfile> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(List<AdministratorProfile> administrators) {
		this.administrators = administrators;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
