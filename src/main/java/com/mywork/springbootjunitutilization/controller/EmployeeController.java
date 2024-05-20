package com.mywork.springbootjunitutilization.controller;

import com.mywork.springbootjunitutilization.model.Employee;
import com.mywork.springbootjunitutilization.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
/*
    A POST request is made to the endpoint handled by this method, with the request body containing the employee data in JSON format.
    Spring converts the JSON request body into an Employee object using the @RequestBody annotation.
    The createEmployee method calls employeeService.saveEmployee(employee), which saves the employee to the database.
    The method returns the saved Employee object.
    The @ResponseStatus(HttpStatus.CREATED) annotation ensures that the HTTP response status is 201 Created, indicating that a new resource has been successfully created.*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployee();
    }




}
