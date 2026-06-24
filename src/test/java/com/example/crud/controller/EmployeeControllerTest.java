package com.example.crud.controller;

import com.example.crud.controller.EmployeeController;
import com.example.crud.entity.Employee;
import com.example.crud.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createEmployeeTest() throws Exception {

        Employee employee =
                new Employee(
                        1L,
                        "Akhilesh",
                        "akhilesh@gmail.com",
                        50000.0
                );

        when(service.save(any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Akhilesh"));
    }

    @Test
    void getAllEmployeesTest() throws Exception {

        when(service.getAll())
                .thenReturn(Arrays.asList(
                        new Employee(
                                1L,
                                "A",
                                "a@gmail.com",
                                1000.0
                        ),
                        new Employee(
                                2L,
                                "B",
                                "b@gmail.com",
                                2000.0
                        )
                ));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()")
                        .value(2));
    }

    @Test
    void getEmployeeByIdTest() throws Exception {

        Employee employee =
                new Employee(
                        1L,
                        "John",
                        "john@gmail.com",
                        40000.0
                );

        when(service.getById(1L))
                .thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("John"));
    }

    @Test
    void updateEmployeeTest() throws Exception {

        Employee employee =
                new Employee(
                        1L,
                        "Updated",
                        "updated@gmail.com",
                        70000.0
                );

        when(service.update(eq(1L), any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Updated"));
    }

    @Test
    void deleteEmployeeTest() throws Exception {

        doNothing().when(service)
                .delete(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Employee deleted successfully"
                ));
    }
}