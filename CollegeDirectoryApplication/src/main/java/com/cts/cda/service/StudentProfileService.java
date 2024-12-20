package com.cts.cda.service;

import java.util.List;

import com.cts.cda.entity.StudentProfile;
import com.cts.cda.models.StudentModel;

public interface StudentProfileService {
	
	List<com.cts.cda.entity.StudentProfile> getAllStudentProfiles();
	void saveStudentProfile(StudentModel studentProfile);
	StudentProfile getStudentProfileById(Long id);
	StudentProfile updateStudentProfile(StudentProfile studentProfile);
	StudentModel getStudentProfileByuserId(Long userId);
	void deleteStudentProfileById(Long id);
	List<StudentModel> getStudentByKey(String key);
	List<StudentModel> getAllStudentModel();
}
