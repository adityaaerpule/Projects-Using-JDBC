package com.manage_student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementGUI extends JFrame {

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField cityField;
    private JTextField idField;
    private JTextArea displayArea;

    public StudentManagementGUI() {
        setTitle("Student Management System");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null); // Using null layout for absolute positioning

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 30);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 20, 200, 30);
        panel.add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 60, 100, 30);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 60, 200, 30);
        panel.add(phoneField);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(20, 100, 100, 30);
        panel.add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(150, 100, 200, 30);
        panel.add(cityField);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(150, 140, 200, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        panel.add(addButton);

        JLabel idLabel = new JLabel("Student ID (for deletion):");
        idLabel.setBounds(20, 180, 200, 30);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(220, 180, 130, 30);
        panel.add(idField);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.setBounds(150, 220, 200, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        panel.add(deleteButton);

        JButton displayButton = new JButton("Display Students");
        displayButton.setBounds(150, 260, 200, 30);
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });
        panel.add(displayButton);

        displayArea = new JTextArea();
        displayArea.setBounds(20, 300, 540, 250);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(20, 300, 540, 250);
        panel.add(scrollPane);

        add(panel);
    }

    private void addStudent() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String city = cityField.getText();
        Student student = new Student(name, phone, city);
        boolean isSuccess = StudentDao.insertStudentToDb(student);
        if (isSuccess) {
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error adding student. Please try again.");
        }
        clearFields();
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            boolean isSuccess = StudentDao.deleteStudent(id);
            if (isSuccess) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting student. Please try again.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numeric value.");
        }
        clearFields();
    }

    private void displayStudents() {
        displayArea.setText("");
        StudentDao.displayStudents(displayArea);
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        cityField.setText("");
        idField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementGUI().setVisible(true);
            }
        });
    }
}
