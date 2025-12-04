package com.example.demo.dto;

import jakarta.validation.constraints.*;

public record EmployeeRequest(

        @NotBlank(message = "Name cannot be empty")
        @Size(min = 3, max = 30, message = "Name must be 3–30 characters")
        String name,

        @NotBlank(message = "Department cannot be empty")
        String department,

        @Positive(message = "Salary must be greater than 0")
        double salary
) {}
