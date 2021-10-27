package com.employee.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.employee.api.dao.DepartmentDao;
import com.employee.api.dao.EmployeeDao;
import com.employee.api.dao.ManagerDao;
import com.employee.api.dto.DepartmentDto;
import com.employee.api.dto.EmployeeDto;
import com.employee.api.dto.ManagerDto;
import com.employee.api.model.Department;
import com.employee.api.model.Employee;
import com.employee.api.model.Manager;
import com.employee.api.service.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private ManagerDao managerDao;

	@Autowired(required = true)
	private ModelMapper modelMapper;

	@Override
	public EmployeeDto save(EmployeeDto employeeDto) {

		try {

			Employee employee = modelMapper.map(employeeDto, Employee.class);

			employee = employeeDao.save(employee);

			employeeDto = modelMapper.map(employee, EmployeeDto.class);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return employeeDto;
	}

	@Override
	public EmployeeDto update(EmployeeDto employeeDto) {

		try {

			Optional<Employee> employee = employeeDao.findById(employeeDto.getId());

			if (employee.isPresent()) {
				Employee emp = employee.get();
				emp.setEmail(employeeDto.getEmail());
				emp.setFirstName(employeeDto.getFirstName());
				emp.setLastName(employeeDto.getLastName());
				emp.setPhoneNumber(employeeDto.getPhoneNumber());
				emp.setSalary(employeeDto.getSalary());

				Department department = new Department();
				department.setId(employeeDto.getDepartment().getId());

				Manager manager = new Manager();
				manager.setId(employeeDto.getManager().getId());

				emp.setDepartment(department);
				emp.setManager(manager);

				emp = employeeDao.save(emp);

				employeeDto = modelMapper.map(emp, EmployeeDto.class);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return employeeDto;
	}

	@Override
	public List<EmployeeDto> findAll(int page, int size) {

		PageRequest pageReq = PageRequest.of(page, size);

		Page<Employee> employees = employeeDao.findAll(pageReq);

		List<EmployeeDto> employess = employees.getContent().stream()
				.map(emp -> modelMapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());

		return employess;
	}

	@Override
	public void delete(int id) {
		employeeDao.delete(id);

	}

	@Override
	public List<DepartmentDto> getAllDepartments() {

		List<Department> departments = departmentDao.findAll();

		List<DepartmentDto> departmentDtos = departments.stream()
				.map(dept -> modelMapper.map(dept, DepartmentDto.class)).collect(Collectors.toList());

		return departmentDtos;
	}

	@Override
	public List<ManagerDto> getAllManagers() {

		List<Manager> managers = managerDao.findAll();

		List<ManagerDto> managerDtos = managers.stream().map(man -> modelMapper.map(man, ManagerDto.class))
				.collect(Collectors.toList());

		return managerDtos;
	}

	@Override
	public EmployeeDto findById(int id) {
		Optional<Employee> employee = employeeDao.findById(id);
		EmployeeDto employeeDto = new EmployeeDto();
		if (employee.isPresent()) {

			employeeDto = modelMapper.map(employee.get(), EmployeeDto.class);
		}
		return employeeDto;
	}

}
