# EnterpriseJavaBasics
Implementation of Basic Enterprise Java Logics.

# Lab-1 Client-Server Dependencies
Download these 2 files.
1. Install IntelliJ IDE.
2. Integrate JDK after installing by adding it to environment.

# Lab-2 Implementation of student-management Project.
Name:             student-management
Language:         Java
Type:             Maven
Package name:     com.example.ucs067.studentmanagement
Dependencies: Spring Web and Spring Boot Dev Tools.




Then we update the Hellocontroller.java

package com.example.ucs067.studentmanagement.controller;

import com.example.ucs067.studentmanagement.Student;
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
        return new Student(
                1,
                "Rahul",
                "rahul@example.com"
        );
    }

}
Try this http://localhost:8081/student

Modify HelloController file

package com.example.ucs067.studentmanagement.controller;

import java.util.List;
import com.example.ucs067.studentmanagement.Student;
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
        return new Student(
                1,
                "Rahul",
                "rahul@example.com"
        );
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
