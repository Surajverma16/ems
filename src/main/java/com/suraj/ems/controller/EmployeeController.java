package com.suraj.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.suraj.ems.dto.EmployeeDto;
import com.suraj.ems.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

// Controller handles the http request and the responses. entry point for the client to interact with the application. 
@RestController // handles the http request and response in json for apis
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeServiceImpl.createNewEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> allEmployees = employeeServiceImpl.getAllEmployees();
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeById = employeeServiceImpl.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable("id") Long employeeId,
            @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeById = employeeServiceImpl.updateEmployeeById(employeeId, employeeDto);
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable ("id") Long employeeId){
        employeeServiceImpl.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Employee Details deleted successfully", HttpStatus.OK);
    }
}
