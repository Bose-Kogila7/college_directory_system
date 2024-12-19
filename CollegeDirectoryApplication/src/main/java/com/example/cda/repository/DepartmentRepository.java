package com.example.cda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cda.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long>{
	Department findByName(String name);
}
