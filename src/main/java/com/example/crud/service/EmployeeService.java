package com.example.crud.service;

import com.example.crud.entity.Employee;
import com.example.crud.exception.EmployeeNotFoundException;
import com.example.crud.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new EmployeeNotFoundException(id)
                );
    }

    public Employee update(Long id, Employee employee) {

        Employee existing = getById(id);

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setSalary(employee.getSalary());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}