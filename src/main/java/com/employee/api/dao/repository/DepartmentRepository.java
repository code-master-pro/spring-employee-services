package com.employee.api.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.api.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
