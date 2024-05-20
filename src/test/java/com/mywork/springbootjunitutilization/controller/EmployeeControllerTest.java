package com.mywork.springbootjunitutilization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mywork.springbootjunitutilization.model.Employee;
import com.mywork.springbootjunitutilization.service.EmployeeService;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class EmployeeControllerTest {

    /* private MockMvc mockMvc;
     MockMvc is a class provided by Spring for testing web endpoints without starting the entire server. It allows you to send mock HTTP requests and verify the responses.
     By using @Autowired, Spring will inject an instance of MockMvc into this field.*/
    @Autowired
    private MockMvc mockMvc;

    /* @MockBean
     This annotation is used in Spring tests to create and inject a mock bean into the application context.
     It replaces any existing bean of the same type in the context with the mock.
     @MockBean is particularly useful for unit testing, as it allows you to mock dependencies and control their behavior.*/
    @MockBean
    private EmployeeService employeeService;

    /*private ObjectMapper objectMapper;
    ObjectMapper is a class from the Jackson library, used for converting Java objects to and from JSON.
    By using @Autowired, Spring will inject an instance of ObjectMapper into this field.*/
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Junit test cases for createEmployee method")
    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception {
//        given - precondition or setup
        Employee employee = Employee.builder().firstName("kavya").lastName("shree").email("kavya@gmail.com").build();
        BDDMockito.given(employeeService.saveEmployee(ArgumentMatchers.any(Employee.class))).willAnswer((invocation -> invocation.getArgument(0)));
//    when - action or behaviour of the testcases
        ResultActions response = mockMvc.perform((post("/api/employees")).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee)));
//    then - verify the output
//    response.andDo(MockMvcResultHandlers.print()) --> we can print the output using this code
        response.andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(employee.getLastName())))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("email", CoreMatchers.is(employee.getEmail())));
    }

    @DisplayName("Junit test cases for getAllEmployees method")
    @Test
    public void givenListOfEmployee_whenGetAllEmployee_thenReturnListOfEmployee()throws Exception{
//    given - pre-condition or setup
        //case:1
      /*  Employee employee = Employee.builder().firstName("kavya").lastName("shree").email("kavya@gmail.com").build();
        Employee employee1 = Employee.builder().firstName("Goutham").lastName("Gowda").email("gg@gmail.com").build();
        Employee employee2 = Employee.builder().firstName("Baby").lastName("Babyshree").email("kgb@gmail.com").build();
        BDDMockito.given(employeeService.getAllEmployee()).willReturn(List.of(employee, employee1,employee2));*/

        //case:2
        List<Employee> listOfEmployee=new ArrayList<>();
        listOfEmployee.add(Employee.builder().firstName("kavya").lastName("shree").email("kavya@gmail.com").build());
        listOfEmployee.add(Employee.builder().firstName("Goutham").lastName("Gowda").email("gg@gmail.com").build());
        listOfEmployee.add(Employee.builder().firstName("Baby").lastName("Babyshree").email("kgb@gmail.com").build());
        BDDMockito.given(employeeService.getAllEmployee()).willReturn(listOfEmployee);
//        when - action or behaviour of the test cases
        ResultActions response=mockMvc.perform(get("/api/employees"));
//        then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(listOfEmployee.size())));
            }
}


