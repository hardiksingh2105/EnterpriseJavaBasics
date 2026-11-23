package com.example.ucs067.studentmanagement.controller;

import com.example.ucs067.studentmanagement.Student;
import com.example.ucs067.studentmanagement.dto.StudentRequest;
import com.example.ucs067.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @GetMapping("/student")
    public Student getPredefinedStudent() {
        // Kept from Lab 2 as a sample endpoint; it is not inserted into PostgreSQL.
        return new Student(1, "Rahul", "rahul@example.com");
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentRequest request) {
        Student savedStudent = studentService.create(request);
        return ResponseEntity.created(URI.create("/students/" + savedStudent.getId())).body(savedStudent);
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable int id, @Valid @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/students/search/name/{name}")
    public List<Student> searchByName(@PathVariable String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/students/search/name/contains/{keyword}")
    public List<Student> searchByNameContaining(@PathVariable String keyword) {
        return studentService.findByNameContaining(keyword);
    }

    @GetMapping("/students/search/email/{email}")
    public List<Student> searchByEmail(@PathVariable String email) {
        return studentService.findByEmail(email);
    }

    @GetMapping("/students/page")
    public Page<Student> getStudentsPage(@PageableDefault(size = 10) Pageable pageable) {
        if (pageable.getPageSize() < 1 || pageable.getPageSize() > 100) {
            throw new IllegalArgumentException("Page size must be between 1 and 100.");
        }
        return studentService.findPage(pageable);
    }

    @GetMapping("/students/sort/name")
    public List<Student> getStudentsSortedByName() {
        return studentService.findSortedByName();
    }
}
