package com.example.ucs067.studentmanagement.service;

import com.example.ucs067.studentmanagement.Course;
import com.example.ucs067.studentmanagement.Student;
import com.example.ucs067.studentmanagement.dto.CourseRequest;
import com.example.ucs067.studentmanagement.exception.ResourceNotFoundException;
import com.example.ucs067.studentmanagement.repository.CourseRepository;
import com.example.ucs067.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Transactional
    public Course addCourseToStudent(int studentId, CourseRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student with ID " + studentId + " was not found."));

        Course course = new Course(request.getCourseName(), request.getCredits());
        student.addCourse(course); // Set the owning side (Course.student) and keep both sides in sync.
        return courseRepository.save(course);
    }
}
