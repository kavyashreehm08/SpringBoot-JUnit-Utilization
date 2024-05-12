package com.mywork.springbootjunitutilization.service;

import com.mywork.springbootjunitutilization.exception.ResourceNotFoundException;
import com.mywork.springbootjunitutilization.model.Employee;
import com.mywork.springbootjunitutilization.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    //@Autowired
    //Note: @Autowired mainly used for injection, whenever we are have single construction the class no need to us @Autowire annotation
private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee>  employee1=employeeRepository.findByEmail(employee.getEmail());
        if(employee1.isPresent()){
            throw  new ResourceNotFoundException("Employee already exist with given email:"+employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee updatedEmployee) {
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }


}
