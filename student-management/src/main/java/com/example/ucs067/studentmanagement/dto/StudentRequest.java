package com.example.ucs067.studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class StudentRequest {

    @Positive(message = "Student ID must be a positive number")
    private int id;

    @NotBlank(message = "Student name is required")
    private String name;

    @NotBlank(message = "Student email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    public StudentRequest() {
    }

    public StudentRequest(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
