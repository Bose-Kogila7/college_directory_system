package com.cts.cda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

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
	Optional<FacultyProfile> findById(long long1);
	ResponseEntity<byte[]> getImage(Long id);
}
