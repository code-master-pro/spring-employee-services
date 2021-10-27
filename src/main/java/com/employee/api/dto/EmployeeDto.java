package com.employee.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDto {

	private int id;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private Date hireDate;

	private BigDecimal salary;

	private DepartmentDto department;

	private ManagerDto manager;

}
