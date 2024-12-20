package com.cts.cda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.cda.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long>{
	Department findByName(String name);
}
