package com.cts.cda.service;

import java.util.List;

import com.cts.cda.entity.AdministratorProfile;
import com.cts.cda.models.AdministratorModel;

public interface AdministratorProfileService {
	List<AdministratorProfile> getAllAdministratorProfiles();
	AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile);
	AdministratorProfile getAdministratorProfileById(Long id);
	AdministratorProfile updateAdministratorProfile(AdministratorProfile administratorProfile);
	AdministratorModel getAdministratorProfileByuserId(Long userId);
	void deleteAdministratorProfileById(Long id);
}
