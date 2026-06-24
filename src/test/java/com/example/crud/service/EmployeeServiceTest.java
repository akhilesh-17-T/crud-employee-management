package com.example.crud.service;

import com.example.crud.entity.Employee;
import com.example.crud.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @Test
    void saveEmployeeTest() {

        Employee employee = new Employee(
                1L,
                "Akhilesh",
                "akhilesh@gmail.com",
                50000.0
        );

        when(repository.save(employee))
                .thenReturn(employee);

        Employee result = service.save(employee);

        assertNotNull(result);
        assertEquals("Akhilesh", result.getName());

        verify(repository, times(1))
                .save(employee);
    }

    @Test
    void getAllEmployeesTest() {

        List<Employee> employees = Arrays.asList(
                new Employee(1L, "A", "a@gmail.com", 1000.0),
                new Employee(2L, "B", "b@gmail.com", 2000.0)
        );

        when(repository.findAll())
                .thenReturn(employees);

        List<Employee> result = service.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void getEmployeeByIdTest() {

        Employee employee = new Employee(
                1L,
                "John",
                "john@gmail.com",
                40000.0
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result = service.getById(1L);

        assertEquals("John", result.getName());
    }

    @Test
    void getEmployeeByIdNotFoundTest() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> service.getById(1L)
                );

        assertEquals(
                "Employee not found with id: 1",
                exception.getMessage()
        );
    }

    @Test
    void updateEmployeeTest() {

        Employee existing = new Employee(
                1L,
                "John",
                "john@gmail.com",
                40000.0
        );

        Employee updated = new Employee(
                1L,
                "Updated",
                "updated@gmail.com",
                70000.0
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(repository.save(any(Employee.class)))
                .thenReturn(updated);

        Employee result =
                service.update(1L, updated);

        assertEquals("Updated", result.getName());
        assertEquals(
                "updated@gmail.com",
                result.getEmail()
        );
        assertEquals(
                70000.0,
                result.getSalary()
        );

        verify(repository, times(1))
                .save(any(Employee.class));
    }

    @Test
    void deleteEmployeeTest() {

        doNothing()
                .when(repository)
                .deleteById(1L);

        service.delete(1L);

        verify(repository, times(1))
                .deleteById(1L);
    }
}