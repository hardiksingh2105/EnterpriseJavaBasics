package com.example.ucs067.studentmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentControllerTest {

    @Test
    void createsReadsAndDeletesStudent() {
        StudentController controller = new StudentController();
        Student newStudent = new Student(4, "Neha", "neha@example.com");

        var createResponse = controller.createStudent(newStudent);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        assertEquals(4, controller.getStudentById(4).getBody().getId());

        var deleteResponse = controller.deleteStudent(4);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, controller.getStudentById(4).getStatusCode());
    }

    @Test
    void duplicateIdsAreRejected() {
        StudentController controller = new StudentController();
        var response = controller.createStudent(new Student(1, "Duplicate", "duplicate@example.com"));
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void deleteUsesIteratorAndReturnsNotFoundForMissingStudent() {
        StudentController controller = new StudentController();
        assertTrue(controller.getAllStudents().getBody().size() == 3);
        assertEquals(HttpStatus.NOT_FOUND, controller.deleteStudent(999).getStatusCode());
    }
}
