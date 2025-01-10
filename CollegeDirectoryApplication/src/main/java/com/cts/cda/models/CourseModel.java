package com.cts.cda.models;

import lombok.Data;

@Data
public class CourseModel {
	private String title;
    private Long departmentId;
    private Long facultyId;
    private String facultyName;

    public CourseModel(String title, Long departmentId, Long facultyId, String facultyName) {
        this.title = title;
        this.departmentId = departmentId;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
    }
	    
	    
}
