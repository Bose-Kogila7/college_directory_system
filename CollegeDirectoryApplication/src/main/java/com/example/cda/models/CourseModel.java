package com.example.cda.models;

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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Long getDepartmentId() {
			return departmentId;
		}
		public void setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
		}
		public Long getFacultyId() {
			return facultyId;
		}
		public void setFacultyId(Long facultyId) {
			this.facultyId = facultyId;
		}
		public String getFacultyName() {
			return facultyName;
		}
		public void setFacultyName(String facultyName) {
			this.facultyName = facultyName;
		}
	    
	    
}
