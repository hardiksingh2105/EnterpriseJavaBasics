package com.example.ucs067.studentmanagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @GetMapping("/student")
    public Student getStudent() {
        return new Student(1, "Rahul", "rahul@example.com");
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return List.of(
                new Student(1, "Rahul", "rahul@example.com"),
                new Student(2, "Priya", "priya@example.com"),
                new Student(3, "Amit", "amit@example.com")
        );
    }
}
