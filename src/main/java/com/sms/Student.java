package com.sms;

import java.util.regex.Pattern;

public final class Student {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    private final String studentId;
    private final String fullName;
    private final String email;
    private final String course;

    public Student(String studentId, String fullName, String email, String course) {
        this.studentId = requireText(studentId, "Student ID");
        this.fullName = requireText(fullName, "Full name");
        this.email = requireText(email, "Email");
        this.course = requireText(course, "Course");

        if (!EMAIL_PATTERN.matcher(this.email).matches()) {
            throw new IllegalArgumentException("Enter a valid email address.");
        }
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        return value.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }
}
