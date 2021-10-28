package com.employee.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeDto {

	private int id;

	@Size(min = 2)
	@NotBlank(message = "First name should be not be null !")
	private String firstName;

	@Size(min = 2)
	@NotBlank(message = "Last name should be not be null !")
	private String lastName;

	@Email
	private String email;

	@Pattern(regexp = "^[0-9-]+$")
	private String phoneNumber;

	private Date hireDate;

	@Min(1)
	private BigDecimal salary;

	private DepartmentDto department;

	private ManagerDto manager;

}
