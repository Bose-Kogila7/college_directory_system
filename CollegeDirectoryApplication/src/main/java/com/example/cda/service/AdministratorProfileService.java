package com.example.cda.service;

import java.util.List;

import com.example.cda.entity.AdministratorProfile;
import com.example.cda.models.AdministratorModel;

public interface AdministratorProfileService {
	List<AdministratorProfile> getAllAdministratorProfiles();
	AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile);
	AdministratorProfile getAdministratorProfileById(Long id);
	AdministratorProfile updateAdministratorProfile(AdministratorProfile administratorProfile);
	AdministratorModel getAdministratorProfileByuserId(Long userId);
	void deleteAdministratorProfileById(Long id);
}
