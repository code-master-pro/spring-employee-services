package com.employee.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.employee.api.controller.EmployeeController;
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

	private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

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

			LOGGER.info("EMPLOYEE SERVER  SAVE() PROCESSING ...");

			LOGGER.info("DATA TO CREATE EMPLOYEE " + employeeDto.toString());

			Employee employee = modelMapper.map(employeeDto, Employee.class);

			employee = employeeDao.save(employee);

			LOGGER.info("DATA AFTER CREATING EMPLOYEE " + employee.toString());

			employeeDto = modelMapper.map(employee, EmployeeDto.class);

		} catch (Exception e) {

			LOGGER.error("EXCEPTION OCCURED IN CREATE EMPLOYEE ", e);
		}

		return employeeDto;
	}

	@Override
	public EmployeeDto update(EmployeeDto employeeDto) {

		try {

			LOGGER.info("EMPLOYEE SERVER  UPDATE() PROCESSING ...");

			LOGGER.info("DATA TO UPDATE EMPLOYEE " + employeeDto.toString());

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

				LOGGER.info("DATA AFTER UPDATE EMPLOYEE " + emp.toString());

				employeeDto = modelMapper.map(emp, EmployeeDto.class);
			}

		} catch (Exception e) {
			LOGGER.error("EXCEPTION OCCURED IN UPDATE EMPLOYEE ", e);
		}

		return employeeDto;
	}

	@Override
	public List<EmployeeDto> findAll(int page, int size) {

		List<EmployeeDto> employess = new ArrayList<>();
		try {

			LOGGER.info("EMPLOYEE SERVER  FINDALL() PROCESSING ...");

			LOGGER.info("REQUEST PAGINATION PAGE " + page);
			LOGGER.info("REQUEST PAGINATION SIZE " + size);

			PageRequest pageReq = PageRequest.of(page, size);

			Page<Employee> employees = employeeDao.findAll(pageReq);

			employess = employees.getContent().stream().map(emp -> modelMapper.map(emp, EmployeeDto.class))
					.collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.error("EXCEPTION OCCURED IN FIND ALL EMPLOYEE ", e);
		}

		return employess;
	}

	@Override
	public void delete(int id) {

		try {

			LOGGER.info("EMPLOYEE SERVER  DELETE() PROCESSING ...");

			LOGGER.info("DELETED EMPLOYEE ID " + id);

			employeeDao.delete(id);

		} catch (Exception e) {

			LOGGER.error("EXCEPTION OCCURED IN DELETE EMPLOYEE ", e);
		}

	}

	@Override
	public List<DepartmentDto> getAllDepartments() {

		LOGGER.info("EMPLOYEE SERVER  GETALLDEPARTMENTS() PROCESSING ...");

		List<DepartmentDto> departmentDtos = null;
		try {

			List<Department> departments = departmentDao.findAll();

			departmentDtos = departments.stream().map(dept -> modelMapper.map(dept, DepartmentDto.class))
					.collect(Collectors.toList());

		} catch (Exception e) {

			LOGGER.error("EXCEPTION OCCURED IN ALL DEPARTMENTS ", e);
		}

		return departmentDtos;
	}

	@Override
	public List<ManagerDto> getAllManagers() {

		LOGGER.info("EMPLOYEE SERVER  GETALLMANAGERS() PROCESSING ...");

		List<ManagerDto> managerDtos = null;
		try {

			List<Manager> managers = managerDao.findAll();

			managerDtos = managers.stream().map(man -> modelMapper.map(man, ManagerDto.class))
					.collect(Collectors.toList());

		} catch (Exception e) {
			LOGGER.error("EXCEPTION OCCURED IN ALL MANAGERS ", e);
		}

		return managerDtos;
	}

	@Override
	public EmployeeDto findById(int id) {

		LOGGER.info("EMPLOYEE SERVER  FINDBYID() PROCESSING ...");

		EmployeeDto employeeDto = null;
		try {

			LOGGER.info(" EMPLOYEE ID " + id);

			Optional<Employee> employee = employeeDao.findById(id);

			if (employee.isPresent()) {

				employeeDto = modelMapper.map(employee.get(), EmployeeDto.class);
			}

		} catch (Exception e) {
			LOGGER.error("EXCEPTION OCCURED FINDING THE EMPLOYEE BY ID ", e);
		}

		return employeeDto;
	}

}
