package com.example.ucs067.studentmanagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
