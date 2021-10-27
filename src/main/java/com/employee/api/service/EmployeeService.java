package com.employee.api.service;

import java.util.List;

import com.employee.api.dto.DepartmentDto;
import com.employee.api.dto.EmployeeDto;
import com.employee.api.dto.ManagerDto;

public interface EmployeeService {

	public EmployeeDto save(EmployeeDto employeeDto);

	public EmployeeDto update(EmployeeDto employeeDto);

	public EmployeeDto findById(int id);

	public List<EmployeeDto> findAll(int page, int size);

	public void delete(int id);

	public List<DepartmentDto> getAllDepartments();

	public List<ManagerDto> getAllManagers();
}
