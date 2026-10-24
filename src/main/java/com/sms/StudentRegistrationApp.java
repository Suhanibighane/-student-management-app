package com.sms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public final class StudentRegistrationApp {
    private StudentRegistrationApp() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createWindow().setVisible(true));
    }

    static JFrame createWindow() {
        StudentService studentService = new StudentService();
        JFrame frame = new JFrame("Student Management - Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(760, 540);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(16, 16));

        JLabel title = new JLabel("Student Registration", SwingConstants.LEFT);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        title.setForeground(new Color(28, 52, 84));
        title.setBorder(BorderFactory.createEmptyBorder(20, 24, 0, 24));
        frame.add(title, BorderLayout.NORTH);

        JTextField idField = createField("studentIdField");
        JTextField nameField = createField("fullNameField");
        JTextField emailField = createField("emailField");
        JTextField courseField = createField("courseField");

        JPanel form = new JPanel(new GridLayout(4, 2, 12, 10));
        form.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        form.add(new JLabel("Student ID"));
        form.add(idField);
        form.add(new JLabel("Full name"));
        form.add(nameField);
        form.add(new JLabel("Email"));
        form.add(emailField);
        form.add(new JLabel("Course"));
        form.add(courseField);

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[]{"Student ID", "Full name", "Email", "Course"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable studentTable = new JTable(tableModel);
        studentTable.setName("studentTable");
        studentTable.setRowHeight(24);

        JLabel status = new JLabel("Enter student details to register.");
        status.setName("statusLabel");
        status.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 24));

        JButton registerButton = new JButton("Register student");
        registerButton.setName("registerButton");
        registerButton.addActionListener(event -> {
            try {
                Student student = new Student(
                        idField.getText(), nameField.getText(), emailField.getText(), courseField.getText());
                studentService.registerStudent(student);
                tableModel.addRow(new Object[]{
                        student.getStudentId(), student.getFullName(), student.getEmail(), student.getCourse()
                });
                status.setForeground(new Color(24, 112, 64));
                status.setText("Student registered successfully.");
            } catch (IllegalArgumentException exception) {
                status.setForeground(new Color(176, 44, 44));
                status.setText(exception.getMessage());
            }
        });

        JButton clearButton = new JButton("Clear form");
        clearButton.addActionListener(event -> {
            idField.setText("");
            nameField.setText("");
            emailField.setText("");
            courseField.setText("");
            status.setForeground(Color.DARK_GRAY);
            status.setText("Enter student details to register.");
        });

        JPanel actions = new JPanel();
        actions.add(registerButton);
        actions.add(clearButton);

        JPanel content = new JPanel(new BorderLayout(10, 12));
        content.add(form, BorderLayout.NORTH);
        content.add(actions, BorderLayout.CENTER);
        content.add(status, BorderLayout.SOUTH);

        JPanel body = new JPanel(new BorderLayout(12, 12));
        body.setBorder(BorderFactory.createEmptyBorder(0, 24, 24, 24));
        body.add(content, BorderLayout.NORTH);
        body.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        frame.add(body, BorderLayout.CENTER);
        return frame;
    }

    private static JTextField createField(String name) {
        JTextField field = new JTextField();
        field.setName(name);
        return field;
    }
}
