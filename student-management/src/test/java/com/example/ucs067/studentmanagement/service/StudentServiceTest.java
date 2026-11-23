package com.example.ucs067.studentmanagement.service;

import com.example.ucs067.studentmanagement.Student;
import com.example.ucs067.studentmanagement.dto.StudentRequest;
import com.example.ucs067.studentmanagement.exception.DuplicateStudentException;
import com.example.ucs067.studentmanagement.exception.ResourceNotFoundException;
import com.example.ucs067.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void createsStudentWhenIdIsAvailable() {
        StudentRequest request = new StudentRequest(21, "Anaya", "anaya@example.com");
        when(studentRepository.existsById(21)).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Student saved = studentService.create(request);

        assertEquals(21, saved.getId());
        assertEquals("Anaya", saved.getName());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    void rejectsDuplicateStudentIdWithoutSaving() {
        StudentRequest request = new StudentRequest(21, "Anaya", "anaya@example.com");
        when(studentRepository.existsById(21)).thenReturn(true);

        assertThrows(DuplicateStudentException.class, () -> studentService.create(request));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void missingStudentThrowsNotFound() {
        when(studentRepository.findById(999)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> studentService.findById(999));
    }
}
