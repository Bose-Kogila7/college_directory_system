package com.cts.cda.service;

import java.util.List;

import com.cts.cda.entity.FacultyProfile;
import com.cts.cda.models.FacultyModel;

public interface FacultyProfileService {

	List<FacultyProfile> getAllFacultyProfiles();
	void saveFacultyProfile(FacultyModel facultyProfile);
	FacultyProfile getFacultyProfileById(Long id);
	FacultyProfile updateFacultyProfile(Long id,FacultyModel updateDTO);
	FacultyModel getFacultyProfileByuserId(Long userId);
	void deleteFacultyProfileById(Long id);
	List<FacultyModel> getAllFacultyModel();
}