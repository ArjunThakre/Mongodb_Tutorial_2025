package com.example.demo.dto;

public record EmployeeResponse(
        String id,
        String name,
        String department,
        double salary
) {}
