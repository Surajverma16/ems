package com.suraj.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    
}
