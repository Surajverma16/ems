package com.suraj.ems.service;

import java.util.List;

import com.suraj.ems.dto.EmployeeDto;

public interface EmployeeService {

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto);
    public List<EmployeeDto> getAllEmployees();
    public EmployeeDto getEmployeeById (Long id);
    public EmployeeDto updaEmployeeById (Long id);
    public void deleteEmployeeById (Long id);
}
