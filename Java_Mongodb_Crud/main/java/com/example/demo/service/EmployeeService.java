package com.example.demo.service;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeResponse create(EmployeeRequest req) {
        Employee emp = Employee.builder()
                .name(req.name())
                .department(req.department())
                .salary(req.salary())
                .build();
        return toResponse(repository.save(emp));
    }

    public List<EmployeeResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public EmployeeResponse getById(String id) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + id));
        return toResponse(emp);
    }

    public EmployeeResponse update(String id, EmployeeRequest req) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + id));

        emp.setName(req.name());
        emp.setDepartment(req.department());
        emp.setSalary(req.salary());

        return toResponse(repository.save(emp));
    }

    public String delete(String id) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + id));
        repository.delete(emp);
        return "Deleted: " + id;
    }

    private EmployeeResponse toResponse(Employee emp) {
        return new EmployeeResponse(emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary());
    }
}
