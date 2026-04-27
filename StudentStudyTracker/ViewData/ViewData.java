package Connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import Connection.UpdateStudent;
import Connection.DeleteStudent;

public class ViewData {

    public static void viewStudentsUI() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Semester");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getInt("semester")
                });
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame("Students List");
            frame.add(scrollPane);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // 🔥 ROW CLICK FEATURE
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {

                    int row = table.rowAtPoint(evt.getPoint());
                    if (row < 0) return;

                    int id = (int) model.getValueAt(row, 0);
                    String name = (String) model.getValueAt(row, 1);
                    int sem = (int) model.getValueAt(row, 2);

                    String[] options = {"Update", "Delete", "Cancel"};

                    int choice = JOptionPane.showOptionDialog(
                            frame,
                            "Choose action for ID: " + id,
                            "Action",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

                    // ✏️ UPDATE
                    if (choice == 0) {
                        String newName = JOptionPane.showInputDialog("Enter New Name:", name);
                        if (newName == null || newName.trim().isEmpty()) return;

                        String newSemStr = JOptionPane.showInputDialog("Enter New Semester:", sem);
                        if (newSemStr == null || newSemStr.trim().isEmpty()) return;

                        try {
                            int newSem = Integer.parseInt(newSemStr);

                            UpdateStudent.updateStudent(id, newName, newSem);

                            JOptionPane.showMessageDialog(frame, "Student Updated!");

                            frame.dispose();
                            viewStudentsUI(); // refresh

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Invalid semester!");
                        }
                    }

                    // ❌ DELETE
                    else if (choice == 1) {
                        int confirm = JOptionPane.showConfirmDialog(
                                frame,
                                "Are you sure you want to delete this student?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            DeleteStudent.deleteStudent(id);

                            JOptionPane.showMessageDialog(frame, "Student Deleted!");

                            frame.dispose();
                            viewStudentsUI(); // refresh
                        }
                    }
                }
            });

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewLogsUI() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM study_log";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Log ID");
            model.addColumn("Student ID");
            model.addColumn("Date");
            model.addColumn("Hours");
            model.addColumn("Stress");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("log_id"),
                        rs.getInt("student_id"),
                        rs.getDate("study_date"),
                        rs.getInt("hours_studied"),
                        rs.getInt("stress_level")
                });
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame("Study Logs");
            frame.add(scrollPane);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}