package com.suraj.ems.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.suraj.ems.dto.EmployeeDto;
import com.suraj.ems.entity.Employee;
import com.suraj.ems.exception.EmployeeResourceNotFoundException;
import com.suraj.ems.mapper.EmployeeMapper;
import com.suraj.ems.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service // provides the business funcationality
@AllArgsConstructor
public class EmployeeServiceImpl {

    private EmployeeRepository employeeRepository; // contected to jpa repository

    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) { // logic to create the new employee
        Employee newEmployee = EmployeeMapper.mapToEmployee(employeeDto); // new employee data to employee mapper
        Employee savedEmployee = employeeRepository.save(newEmployee); // saved the employee in the database
        return EmployeeMapper.maptoEmployeeDto(savedEmployee);
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees.stream().map((e) -> EmployeeMapper.maptoEmployeeDto(e)).collect(Collectors.toList());

    }

    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeResourceNotFoundException("Request Employee Id not found"));
                return EmployeeMapper.maptoEmployeeDto(employee);
    }

    public EmployeeDto updateEmployeeById (Long id, EmployeeDto employeeDto){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeResourceNotFoundException("Employee Id requested doesnt exist..."));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employeeRepository.save(employee);
        return EmployeeMapper.maptoEmployeeDto(employee);
    }

    public void deleteEmployeeById(Long Id){
        employeeRepository.findById(Id).orElseThrow(() -> new EmployeeResourceNotFoundException("No ID"));
        employeeRepository.deleteById(Id);
    }
}
