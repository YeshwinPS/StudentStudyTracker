package ui;

import Connection.*;
import javax.swing.*;
import java.awt.*;

public class MainMenuUI extends JFrame {

    public MainMenuUI() {

        setTitle("Student Study Tracker");
        setSize(400, 400);
        setLayout(new GridLayout(7,1));

        JButton btnAddStudent = new JButton("Add Student");
        JButton btnAddLog = new JButton("Add Study Log");
        JButton btnViewStudents = new JButton("View Students");
        JButton btnViewLogs = new JButton("View Logs");
        JButton btnUpdate = new JButton("Update Student");
        JButton btnDelete = new JButton("Delete Student");
        JButton btnExit = new JButton("Exit");

        add(btnAddStudent);
        add(btnAddLog);
        add(btnViewStudents);
        add(btnViewLogs);
        add(btnUpdate);
        add(btnDelete);
        add(btnExit);

        // ADD STUDENT
        btnAddStudent.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter Name:");
            int sem = Integer.parseInt(JOptionPane.showInputDialog("Enter Semester:"));

            AddStudent.addStudent(name, sem);
        });

        // ADD LOG
        btnAddLog.addActionListener(e -> {
            int hours = Integer.parseInt(JOptionPane.showInputDialog("Hours Studied:"));
            int stress = Integer.parseInt(JOptionPane.showInputDialog("Stress Level (1-5):"));

            AddStudyLog.addLog(hours, stress);
        });

        // VIEW STUDENTS
        btnViewStudents.addActionListener(e -> {
            ViewData.viewStudentsUI();
        });

        // VIEW LOGS
        btnViewLogs.addActionListener(e -> {
            ViewData.viewLogsUI();
        });
        btnUpdate.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID:"));
            String name = JOptionPane.showInputDialog("Enter New Name:");
            int sem = Integer.parseInt(JOptionPane.showInputDialog("Enter New Semester:"));

            UpdateStudent.updateStudent(id, name, sem);

            JOptionPane.showMessageDialog(this, "Student Updated!");
        });
        btnDelete.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID to Delete:"));

            DeleteStudent.deleteStudent(id);

            JOptionPane.showMessageDialog(this, "Student Deleted!");
        });

        // EXIT
        btnExit.addActionListener(e -> System.exit(0));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}