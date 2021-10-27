package com.employee.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.api.dao.repository.ManagerRepository;
import com.employee.api.model.Manager;

@Repository
public class ManagerDao {

	@Autowired
	private ManagerRepository managerRepository;

	public List<Manager> findAll() {
		return managerRepository.findAll();
	}

}
