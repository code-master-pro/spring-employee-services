package com.employee.api.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.api.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
