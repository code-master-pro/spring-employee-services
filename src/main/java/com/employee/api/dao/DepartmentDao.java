package com.employee.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.api.dao.repository.DepartmentRepository;
import com.employee.api.model.Department;

@Repository
public class DepartmentDao {
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}
}
