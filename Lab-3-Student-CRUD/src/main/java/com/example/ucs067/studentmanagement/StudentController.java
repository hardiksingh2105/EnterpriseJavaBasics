package com.example.ucs067.studentmanagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Rahul", "rahul@example.com"),
            new Student(2, "Priya", "priya@example.com"),
            new Student(3, "Amit", "amit@example.com")
    ));

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        if (student == null || student.getId() <= 0
                || isBlank(student.getName()) || isBlank(student.getEmail())) {
            return ResponseEntity.badRequest().body("Student ID, name and email are required; ID must be positive.");
        }
        if (findStudent(student.getId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("A student with ID " + student.getId() + " already exists.");
        }

        students.add(student);
        return ResponseEntity.created(URI.create("/students/" + student.getId())).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        if (updatedStudent == null || updatedStudent.getId() != id) {
            return ResponseEntity.badRequest().body("The body student ID must match the URL ID.");
        }
        if (isBlank(updatedStudent.getName()) || isBlank(updatedStudent.getEmail())) {
            return ResponseEntity.badRequest().body("Student name and email are required.");
        }

        Student existingStudent = findStudent(id);
        if (existingStudent == null) {
            return ResponseEntity.notFound().build();
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        return ResponseEntity.ok(existingStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    private Student findStudent(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
