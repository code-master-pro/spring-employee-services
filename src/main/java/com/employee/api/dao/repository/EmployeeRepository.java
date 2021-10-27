package com.employee.api.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
