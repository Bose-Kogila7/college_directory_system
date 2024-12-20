package com.cts.cda.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.cda.entity.AdministratorProfile;
import com.cts.cda.models.AdministratorModel;
import com.cts.cda.repository.AdministratorProfileRepository;
import com.cts.cda.service.AdministratorProfileService;

@Service
public class AdministratorProfileServiceImpl implements AdministratorProfileService {

	public AdministratorProfileRepository  administratorProfileRepository;
	public AdministratorProfileServiceImpl(AdministratorProfileRepository administratorProfileRepository) {
		super();
		this.administratorProfileRepository = administratorProfileRepository;
	}

	@Override
	public List<AdministratorProfile> getAllAdministratorProfiles() {
		return administratorProfileRepository.findAll();
	}

	@Override
	public AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile) {
		return administratorProfileRepository.save(administratorProfile);
	}

	@Override
	public AdministratorProfile getAdministratorProfileById(Long id) {
		return administratorProfileRepository.findById(id).get();
	}

	@Override
	public AdministratorProfile updateAdministratorProfile(AdministratorProfile administratorProfile) {
		return administratorProfileRepository.save(administratorProfile);
	}

	@Override
	public void deleteAdministratorProfileById(Long id) {
		administratorProfileRepository.deleteById(id);
	}

	@Override
	public AdministratorModel getAdministratorProfileByuserId(Long userId) {
		return administratorProfileRepository.findAdministratorByUserId(userId);
	}


}
