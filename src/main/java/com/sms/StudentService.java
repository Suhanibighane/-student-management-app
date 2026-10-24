package com.sms;

import java.util.ArrayList;
import java.util.List;

public final class StudentService {
    private final List<Student> students = new ArrayList<>();

    public Student registerStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student details are required.");
        }

        boolean duplicateId = students.stream()
                .anyMatch(existing -> existing.getStudentId().equalsIgnoreCase(student.getStudentId()));
        if (duplicateId) {
            throw new IllegalArgumentException("A student with this ID is already registered.");
        }

        boolean duplicateEmail = students.stream()
                .anyMatch(existing -> existing.getEmail().equalsIgnoreCase(student.getEmail()));
        if (duplicateEmail) {
            throw new IllegalArgumentException("A student with this email is already registered.");
        }

        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return List.copyOf(students);
    }
}
