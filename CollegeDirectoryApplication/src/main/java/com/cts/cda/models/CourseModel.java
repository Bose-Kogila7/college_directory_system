package com.cts.cda.models;

import lombok.Data;

@Data
public class CourseModel {
	private Long id;
	private String title;
    private Long departmentId;
    private Long facultyId;
    private String description;
    private String facultyName;

    public CourseModel(String title, Long departmentId, Long facultyId, String facultyName) {
    	this.title = title;
        this.departmentId = departmentId;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
    }
    public CourseModel(Long id,String title,String description, Long departmentId, Long facultyId, String facultyName) {
    	this.id=id;
    	this.title = title;
    	this.description=description;
        this.departmentId = departmentId;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
    }
	    
	    
}
