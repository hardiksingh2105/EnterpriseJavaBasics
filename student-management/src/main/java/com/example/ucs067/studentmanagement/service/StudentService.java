package com.example.ucs067.studentmanagement.service;

import com.example.ucs067.studentmanagement.Student;
import com.example.ucs067.studentmanagement.dto.StudentRequest;
import com.example.ucs067.studentmanagement.exception.DuplicateStudentException;
import com.example.ucs067.studentmanagement.exception.ResourceNotFoundException;
import com.example.ucs067.studentmanagement.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student findById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " was not found."));
    }

    @Transactional
    public Student create(StudentRequest request) {
        if (studentRepository.existsById(request.getId())) {
            throw new DuplicateStudentException("Student with ID " + request.getId() + " already exists.");
        }
        Student student = new Student(request.getId(), request.getName(), request.getEmail());
        return studentRepository.save(student);
    }

    @Transactional
    public Student update(int id, StudentRequest request) {
        if (request.getId() != id) {
            throw new IllegalArgumentException("The body student ID must match the URL ID.");
        }

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " was not found."));

        existingStudent.setName(request.getName());
        existingStudent.setEmail(request.getEmail());
        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void delete(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with ID " + id + " was not found."));
        studentRepository.delete(student);
    }

    @Transactional(readOnly = true)
    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Student> findByNameContaining(String keyword) {
        return studentRepository.findByNameContaining(keyword);
    }

    @Transactional(readOnly = true)
    public List<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Page<Student> findPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Student> findSortedByName() {
        return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
