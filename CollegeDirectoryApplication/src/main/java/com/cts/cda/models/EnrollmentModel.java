package com.cts.cda.models;

import lombok.Data;

@Data
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
	
}
