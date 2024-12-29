package com.cts.cda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.cda.entity.Department;
import com.cts.cda.entity.FacultyProfile;
import com.cts.cda.entity.User;
import com.cts.cda.models.FacultyModel;
import com.cts.cda.repository.DepartmentRepository;
import com.cts.cda.repository.FacultyProfileRespository;
import com.cts.cda.repository.UserRepository;
import com.cts.cda.service.FacultyProfileService;

@Service
public class FacultyProfileServiceImpl implements FacultyProfileService {

private FacultyProfileRespository facultyProfileRepository;
private UserRepository userRepository;
private DepartmentRepository departmentRepository;
private PasswordEncoder passwordEncoder;
	public FacultyProfileServiceImpl(FacultyProfileRespository facultyProfileRepository,UserRepository userRepository,DepartmentRepository departmentRepository, PasswordEncoder passwordEncoder) {
		super();
		this.facultyProfileRepository = facultyProfileRepository;
		this.userRepository = userRepository;
		this.departmentRepository = departmentRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<FacultyProfile> getAllFacultyProfiles() {
		return facultyProfileRepository.findAll();
	}

	@Override
	public void saveFacultyProfile(FacultyModel facultyModel) {
		User user = new User(facultyModel.getId(),facultyModel.getName(),"faculty",facultyModel.getName(),facultyModel.getEmail(),facultyModel.getPhone());
		user.setPassword(passwordEncoder.encode(facultyModel.getPassword()));
		user = userRepository.save(user);
	    Department department = departmentRepository.findById(facultyModel.getDepartmentId())
	            .orElseThrow(() -> new RuntimeException("Department not found"));
		FacultyProfile fp = new FacultyProfile();
		
		fp.setUser(user);
		fp.setDepartment(department);
		fp.setOfficeHours(facultyModel.getOfficeHours());
		fp.setPassword(passwordEncoder.encode(facultyModel.getPassword()));
		fp.setPhoto(facultyModel.getPhoto());
		facultyProfileRepository.save(fp);
	}

	@Override
	public FacultyProfile getFacultyProfileById(Long id) {
		return facultyProfileRepository.findById(id).get();
	}


	@Override
	public void deleteFacultyProfileById(Long id) {
		facultyProfileRepository.deleteById(id);
	}

	@Override
	public FacultyModel getFacultyProfileByuserId(Long userId) {
		return facultyProfileRepository.findFacultyByUserId(userId);
	}
	
	@Override
	public FacultyProfile updateFacultyProfile(Long id,FacultyModel updateDTO) {
		User user = userRepository.findById(id).get();
		user.setEmail(updateDTO.getEmail());
        user.setPhone(updateDTO.getPhone());
        user.setName(updateDTO.getName());
        user.setPassword(passwordEncoder.encode(updateDTO.getPassword()));
        user = userRepository.save(user);
        
		FacultyModel fp = facultyProfileRepository.findFacultyByUserId(id);
		FacultyProfile fac = facultyProfileRepository.findById(fp.getId()).get();
		 Department department = departmentRepository.findById(updateDTO.getDepartmentId())
		            .orElseThrow(() -> new RuntimeException("Department not found"));
		fac.setOfficeHours(updateDTO.getOfficeHours());
        fac.setPhoto(updateDTO.getPhoto());
        fac.setPassword(passwordEncoder.encode(updateDTO.getPassword()));
        fac.setUser(user);
        fac.setDepartment(department);
		
		return facultyProfileRepository.save(fac);
	}

	@Override
	public List<FacultyModel> getAllFacultyModel() {
		// TODO Auto-generated method stub
		return facultyProfileRepository.findAllFacultyModel();
	}

	@Override
	public Optional<FacultyProfile> findById(long id) {
		// TODO Auto-generated method stub
		return facultyProfileRepository.findById(id);
	}
	
	
}
