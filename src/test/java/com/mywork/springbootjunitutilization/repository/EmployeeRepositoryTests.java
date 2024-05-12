package com.mywork.springbootjunitutilization.repository;

import com.mywork.springbootjunitutilization.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
    }

    //Junit test for save employee operation
@DisplayName("Junit test for save employee operation")//changing Junit test name, this name will be displayed in console while Running
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
        //given pre-condition or setup
        //Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
        //when - action or the behaviour that we are going to test
        Employee savedEmployee= employeeRepository.save(employee);
        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    //Junit test for get all employee operation
    @DisplayName("Junit test for get all employees operation")
@Test
    public void giveEmployeesList_whenFindAll_thenEmployeeList(){
    //give - pre-condition and setup
        // Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
    Employee employee2=Employee.builder().firstName("GG").lastName("ramalingegowda").email("goutham@gmail.com").build();
    Employee employee3=Employee.builder().firstName("Nadya").lastName("Rakshith").email("nadya@gmil.com").build();

    employeeRepository.save(employee);
    employeeRepository.save(employee2);
    employeeRepository.save(employee3);
    //when - action or the behaviour that we are going to test
    List<Employee> employeeList=employeeRepository.findAll();

    //then - verify the output
    assertThat(employeeList).isNotNull();
    assertThat(employeeList).size().isEqualTo(3);
    }

    //Junit test for get employee by id operation
    @DisplayName("Junit test for get employee by id operation")
  @Test
    public void giveEmployeeObject_whenFindById_thenReturnEmployeeObject(){

      //give - pre-condition or setup
        // Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
      employeeRepository.save(employee);

      // when - action or behaviour of the test cases
      Employee employeeDB = employeeRepository.findById(employee.getId()).get();
      //then - verify the output
      assertThat(employeeDB).isNotNull();
    }

    //Junit test for get employee by email operation
    @DisplayName("Junit test for get employee by email operation")
    @Test
    public void giveEmployeeEmail_whenFindByEmail_thenReturnEmail(){

        //give - pre-conditions or setup
        //Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
        employeeRepository.save(employee);
        //when - action or behaviour of the test cases
        Employee employee1=employeeRepository.findByEmail(employee.getEmail()).get();
        //then - verifying the output
        assertThat(employee1).isNotNull();
    }

    //Junit test for update employee operation
    @DisplayName("Junit test for update employee operation")
    @Test
     public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        //give - pre-condition or set up
        //Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
        employeeRepository.save(employee);
        //when - action or behaviour of the test case
        Employee saveEmployee=employeeRepository.findById(employee.getId()).get();
        saveEmployee.setFirstName("yashuwife");
        saveEmployee.setEmail("yashuwife@gmail.com");
        Employee updateEmployee =employeeRepository.save(saveEmployee);
        //then - verify the output
        assertThat(updateEmployee.getFirstName()).isEqualTo("yashuwife");
        assertThat(updateEmployee.getEmail()).isEqualTo("yashuwife@gmail.com");
     }
     //Junit test for Delete employee operation
    @DisplayName("Junit test for Delete employee operation")
     @Test
     public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){
        //given - pre-condition or set up
       // Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
         employeeRepository.save(employee);
         //when - action or behaviour of test cases
        employeeRepository.deleteById(employee.getId());
         Optional<Employee> employeeOptional=employeeRepository.findById(employee.getId());
         //then - verify the output
       assertThat(employeeOptional).isEmpty();
     }

     //Junit test for custom query using JPQL with index
    @DisplayName("Junit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){

    //given - pre-conditions or setup
        //Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
        employeeRepository.save(employee);
       /* String firstName="Savithri";
        String lastName="Avva";*/
        //when - action or behaviour of the test cases
        Employee saveEmployee=employeeRepository.findByJPQL(employee.getFirstName(),employee.getLastName());

        //then - verifying the output
        assertThat(saveEmployee).isNotNull();
    }
    //Junit test for custom query using JPQL with named params
    @DisplayName("Junit test for custom query using JPQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParam_thenReturnEmployeeObject(){

    //given - pre-conditions or setup
        //Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
        employeeRepository.save(employee);
        /*String firstName ="Kavyashree";
        String lastName="HM";*/
        //when - action or behaviour of testcase
        Employee saveEmployee=employeeRepository.findByJPQLNamedParams(employee.getFirstName(),employee.getLastName());
        //then - verify the output
        assertThat(saveEmployee).isNotNull();
    }

    @DisplayName("Junit test for custom query using Native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){

        //given - pre-conditions or setup
       // Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
        employeeRepository.save(employee);
        //String firstName ="Gagana";
        //String lastName="Gowda";
        //when - action or behaviour of testcase
        Employee saveEmployee=employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());
        //then - verify the output
        assertThat(saveEmployee).isNotNull();
    }

    @DisplayName("Junit test for custom query using Native SQL with Named Params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){

        //given - pre-conditions or setup
        //Employee employee=Employee.builder().firstName("Kavyashree").lastName("HM").email("kavya@gmail.com").build();
        employeeRepository.save(employee);

        //when - action or behaviour of testcase
        Employee saveEmployee=employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());
        //then - verify the output
        assertThat(saveEmployee).isNotNull();
    }


}
