package com.example.cda.service;

import java.util.List;

import com.example.cda.entity.StudentProfile;
import com.example.cda.models.StudentModel;

public interface StudentProfileService {
	
	List<com.example.cda.entity.StudentProfile> getAllStudentProfiles();
	void saveStudentProfile(StudentModel studentProfile);
	StudentProfile getStudentProfileById(Long id);
	StudentProfile updateStudentProfile(StudentProfile studentProfile);
	StudentModel getStudentProfileByuserId(Long userId);
	void deleteStudentProfileById(Long id);
	List<StudentModel> getStudentByKey(String key);
	List<StudentModel> getAllStudentModel();
}
