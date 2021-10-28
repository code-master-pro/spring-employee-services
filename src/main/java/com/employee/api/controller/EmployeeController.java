package com.employee.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.api.common.ResponseGenericDTO;
import com.employee.api.common.ResponseHandler;
import com.employee.api.dto.DepartmentDto;
import com.employee.api.dto.EmployeeDto;
import com.employee.api.dto.ManagerDto;
import com.employee.api.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

	@RequestMapping(value = "/save-employee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseGenericDTO saveUsers(@Valid @RequestBody EmployeeDto employeeDto) {

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: SAVEUSERS() STARTED....");

		employeeDto = employeeService.save(employeeDto);

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: SAVEUSERS() ENDED.....");

		return ResponseHandler.responseSuccessful(employeeDto);

	}

	@RequestMapping(value = "/update-employee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseGenericDTO updateUsers(@Valid @RequestBody EmployeeDto employeeDto) {

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: UPDATEUSERS() STARTED....");

		employeeDto = employeeService.update(employeeDto);

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: UPDATEUSERS() ENDED.....");

		return ResponseHandler.responseSuccessful(employeeDto);

	}

	@RequestMapping(value = "/delete-employee", method = RequestMethod.DELETE)
	public ResponseGenericDTO deleteEmployee(@RequestParam("employeeId") @Min(1) int employeeId) {

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: deleteEmployee() STARTED....");

		employeeService.delete(employeeId);

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: deleteEmployee() ENDED.....");

		return ResponseHandler.responseSuccessful();

	}

	@RequestMapping(value = "/find-employee", method = RequestMethod.GET)
	public ResponseGenericDTO findEmployee(@RequestParam("employeeId") @Min(1) int employeeId) {

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: findEmployee() STARTED....");

		EmployeeDto employeeDto = employeeService.findById(employeeId);

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: findEmployee() ENDED.....");

		return ResponseHandler.responseSuccessful(employeeDto);

	}

	@RequestMapping(value = "/get-employees", method = RequestMethod.GET)
	public ResponseGenericDTO getEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: deleteEmployee() STARTED....");

		List<EmployeeDto> employees = employeeService.findAll(page, size);

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: deleteEmployee() ENDED.....");

		return ResponseHandler.responseSuccessful(employees);

	}

	@RequestMapping(value = "/get-departments", method = RequestMethod.GET)
	public ResponseGenericDTO getDepartments() {

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: getDepartments() STARTED....");

		List<DepartmentDto> departmentDtos = employeeService.getAllDepartments();

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: getDepartments() ENDED.....");

		return ResponseHandler.responseSuccessful(departmentDtos);

	}

	@RequestMapping(value = "/get-managers", method = RequestMethod.GET)
	public ResponseGenericDTO getManagers() {

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: getManagers() STARTED....");

		List<ManagerDto> managerDtos = employeeService.getAllManagers();

		LOGGER.info("APPLICATION EMPLOYEE CONTROLLER :: getManagers() ENDED.....");

		return ResponseHandler.responseSuccessful(managerDtos);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;
	}

}
