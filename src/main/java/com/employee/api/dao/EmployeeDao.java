package com.employee.api.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.employee.api.dao.repository.EmployeeRepository;
import com.employee.api.model.Employee;

@Repository
public class EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> findById(int id) {
		return employeeRepository.findById(id);
	}

	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	public void delete(int id) {
		employeeRepository.deleteById(id);
	}

}
