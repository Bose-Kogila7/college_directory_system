package com.example.cda.models;

public class EnrollmentModel {
	
	private Long id;
	private Long student_id;
	private Long course_id;
	public EnrollmentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EnrollmentModel(Long id, Long student_id, Long course_id) {
		super();
		this.id = id;
		this.student_id = student_id;
		this.course_id = course_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	
	
}
