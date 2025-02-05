package com.suraj.ems.mapper;

import com.suraj.ems.dto.EmployeeDto;
import com.suraj.ems.entity.Employee;


// Mapper is used to map the objects from one model to another model (employee to employeedto and vice versa)
public class EmployeeMapper {

    public static EmployeeDto maptoEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail());
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail());
    }
}

