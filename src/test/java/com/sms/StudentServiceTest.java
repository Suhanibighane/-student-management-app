package com.sms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class StudentServiceTest {
    @Test
    void registersStudentAndReturnsAnImmutableList() {
        StudentService service = new StudentService();
        Student student = new Student("S101", "Asha Patil", "asha@example.com", "Computer Science");

        assertEquals(student, service.registerStudent(student));
        assertEquals(List.of(student), service.getAllStudents());
        assertThrows(UnsupportedOperationException.class, () -> service.getAllStudents().clear());
    }

    @Test
    void rejectsDuplicateStudentIdsWithoutAddingAnotherStudent() {
        StudentService service = new StudentService();
        service.registerStudent(new Student("S101", "Asha Patil", "asha@example.com", "Computer Science"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.registerStudent(
                        new Student("s101", "Mira Shah", "mira@example.com", "Information Technology")));

        assertTrue(exception.getMessage().contains("ID is already registered"));
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    void rejectsDuplicateEmailsWithoutAddingAnotherStudent() {
        StudentService service = new StudentService();
        service.registerStudent(new Student("S101", "Asha Patil", "asha@example.com", "Computer Science"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.registerStudent(
                        new Student("S102", "Mira Shah", "ASHA@example.com", "Information Technology")));

        assertTrue(exception.getMessage().contains("email is already registered"));
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    void rejectsInvalidEmailAddresses() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Student("S101", "Asha Patil", "not-an-email", "Computer Science"));
    }
}
