package com.example.ucs067.studentmanagement.controller;

import com.example.ucs067.studentmanagement.Course;
import com.example.ucs067.studentmanagement.dto.CourseRequest;
import com.example.ucs067.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Course> addCourseToStudent(
            @PathVariable int studentId,
            @Valid @RequestBody CourseRequest request) {
        Course savedCourse = courseService.addCourseToStudent(studentId, request);
        return ResponseEntity.created(URI.create("/courses/" + savedCourse.getId())).body(savedCourse);
    }
}
