package com.cts.cda.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.cda.entity.Department;
import com.cts.cda.models.DepartmentModel;
import com.cts.cda.repository.DepartmentRepository;
import com.cts.cda.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<DepartmentModel> getAllDepartments() {
		// TODO Auto-generated method stub
		List<Department> departments = departmentRepository.findAll();
		List<DepartmentModel> departmentModels = new ArrayList<>();
		for (Department department : departments) {
			DepartmentModel model = new DepartmentModel(department.getId(), department.getName(),
					department.getDescription());
			departmentModels.add(model);
		}
		return departmentModels;
	}

}
