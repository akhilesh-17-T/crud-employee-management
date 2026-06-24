package com.example.crud.repository;

import com.example.crud.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    void saveEmployeeTest() {
        Employee employee = new Employee(
                null,
                "Akhilesh",
                "akhilesh@gmail.com",
                50000.0
        );

        Employee saved = repository.save(employee);

        assertNotNull(saved.getId());
        assertEquals("Akhilesh", saved.getName());
    }
}