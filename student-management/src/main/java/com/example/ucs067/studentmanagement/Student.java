package com.example.ucs067.studentmanagement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Positive(message = "Student ID must be a positive number")
    private int id;

    @NotBlank(message = "Student name is required")
    private String name;

    @NotBlank(message = "Student email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public Student() {
    }

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course must not be null.");
        }
        courses.add(course);
        course.setStudent(this);
    }

    public void removeCourse(Course course) {
        if (course != null && courses.remove(course)) {
            course.setStudent(null);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses == null ? new ArrayList<>() : courses;
        for (Course course : this.courses) {
            course.setStudent(this);
        }
    }
}
