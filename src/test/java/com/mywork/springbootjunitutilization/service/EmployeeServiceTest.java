package com.mywork.springbootjunitutilization.service;

import com.mywork.springbootjunitutilization.exception.ResourceNotFoundException;
import com.mywork.springbootjunitutilization.model.Employee;
import com.mywork.springbootjunitutilization.repository.EmployeeRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    //initializing mocking class EmployeeRepository (mocking repository class)
    //and initializing EmployeeService for testing (testing service class)
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
        //mocking EmployeeRepository class
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //injecting employeeRepository to EmployeeServiceImpl through constructor or setter injection
        //here we are using constructor injection
       // employeeService=new EmployeeServiceImpl(employeeRepository);

        employee=Employee.builder().id(1L).firstName("Goutham").lastName("Gowda").email("gg@gmail.com").build();

    }

    //Junit test case for saveEmployee method
    @DisplayName("Junit test case for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
         //given - operation or setup
        //Employee employee=Employee.builder().id(1L).firstName("Goutham").lastName("Gowda").email("gg@gmail.com").build();
        //next step is finding how many methods need to stub, here we are stubbing 2 methods i,e employeeRepository.findByEmail and employeeRepository.save(employee)
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);
        //System.out.println("IM PRINTING employeeRepository: " +employeeRepository);
        //System.out.println("IM PRINTING employeeService: " +employeeService);
        //when - action or behaviour of the testcases
        Employee saveEmployee=employeeService.saveEmployee(employee);
        //System.out.println("IM PRINTING saveEmployee" +saveEmployee);
        //then - verifying the output
        assertThat(saveEmployee).isNotNull();
    }


    //Junit test case for saveEmployee method which throw  Exception
    @DisplayName("Junit test case for saveEmployee method which throw  Exception")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenThrowsException(){
        //given - operation or setup
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
       // given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or behaviour of the testcases
        Assertions.assertThrows(ResourceNotFoundException.class, () ->employeeService.saveEmployee(employee));

        //then - verifying the output
       verify(employeeRepository, never()).save(any(Employee.class));
    }

    //Junit Test cases for getAllEmployee method
    @DisplayName("Junit Test cases for getAllEmployee method")
    @Test
    public void givenEmployeeList_whenGetAllEmployee_thenReturnListOfEmployees(){
        //given - pre-condition or setup
        //go to getAllEmployee method in EmployeeServiceImpl its internally calling employeeRepository.findAll()
        //next step is finding how many methods need to stub
        Employee employee1=Employee.builder().id(1L).firstName("Kavya").lastName("Shree").email("kavya@gmail.com").build();
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));
        //when - action or behaviour of test cases
        List<Employee> employeeList = employeeService.getAllEmployee();
        //then - verifying the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    //Junit test cases for getAllEmployee method negative scenario
    @DisplayName("Junit test cases for getAllEmployee method negative scenario")
    @Test
    public  void givenEmptyEmployeeList_whenGetAllEmployee_thenReturnEmptyEmployeeList(){
        //given - pre-condition or setup
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());
        //when - action or behaviour of the test case
        List<Employee> employeeList=employeeService.getAllEmployee();
        //then - verifying the output
        org.assertj.core.api.Assertions.assertThat(employeeList).isEmpty();
        org.assertj.core.api.Assertions.assertThat(employeeList.size()).isEqualTo(0);
    }

    //Junit test cases for getEmployeeById method
    @DisplayName("Junit test cases for getEmployeeById method")
    @Test
   public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        //given - pre-condition or setup
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
        //when - action or behaviour of the test case
       Employee saveEmployee = employeeService.getEmployeeById(employee.getId()).get();
        //then - verifying the output
        assertThat(saveEmployee).isNotNull();

    }

    //Junit test cases for updateEmployee method
    @DisplayName("Junit test cases for updateEmployee method")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        //given - pre-condition or setup
        given(employeeRepository.save(employee)).willReturn(employee);
        employee.setEmail("goutham@gamil.com");
        employee.setFirstName("Kavya");

        //when - action or behaviour of the test cases
        Employee updateEmployee=employeeService.updateEmployee(employee);
        //then - verifying the output
        assertThat(updateEmployee.getEmail()).isEqualTo("goutham@gamil.com");
        assertThat(updateEmployee.getFirstName()).isNotNull();
        assertThat(updateEmployee.getFirstName()).isEqualTo("Kavya");
    }


    //Junit test cases for deleteEmployee method
    //deleteEmployee method is little different then other methods it won't return anything so using void
    @DisplayName("Junit test cases for deleteEmployee method")
    @Test
   public void givenEmployeeId_whenDeleteEmployee_thenNothing(){
        long employeeId=1L;
        //given - pre-condition or setup
       willDoNothing().given(employeeRepository).deleteById(employeeId);
        //when - action or behaviour of the test cases
        employeeService.deleteEmployee(employeeId);
        //then - verifying the output
        verify(employeeRepository,times(1)).deleteById(employeeId);

    }
}
