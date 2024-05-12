package com.mywork.springbootjunitutilization.service;

import com.mywork.springbootjunitutilization.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

    public Optional<Employee> getEmployeeById(long id);

    Employee updateEmployee(Employee updatedEmployee);

    void deleteEmployee(long id);

}
