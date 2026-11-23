package com.example.ucs067.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CourseRequest {

    @NotBlank(message = "Course name is required")
    private String courseName;

    @Positive(message = "Course credits must be a positive number")
    private int credits;

    public CourseRequest() {
    }

    public CourseRequest(String courseName, int credits) {
        this.courseName = courseName;
        this.credits = credits;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
