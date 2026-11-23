package com.example.ucs067.studentmanagement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelloControllerTest {

    private final HelloController controller = new HelloController();

    @Test
    void helloReturnsExpectedGreeting() {
        assertEquals("Hello, Spring Boot!", controller.hello());
    }

    @Test
    void predefinedStudentIsRahul() {
        Student student = controller.getStudent();
        assertEquals(1, student.getId());
        assertEquals("Rahul", student.getName());
        assertEquals("rahul@example.com", student.getEmail());
    }

    @Test
    void studentsEndpointReturnsThreeStudents() {
        var students = controller.getStudents();
        assertNotNull(students);
        assertEquals(3, students.size());
        assertEquals("Priya", students.get(1).getName());
    }
}
