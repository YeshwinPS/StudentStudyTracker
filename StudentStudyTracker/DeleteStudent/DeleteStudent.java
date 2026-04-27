package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteStudent {

    public static void deleteStudent(int id) {
        try {
            Connection con = DBConnection.getConnection();

            // 1️⃣ Delete child records first
            String deleteLogs = "DELETE FROM study_log WHERE student_id=?";
            PreparedStatement ps1 = con.prepareStatement(deleteLogs);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // 2️⃣ Then delete student
            String deleteStudent = "DELETE FROM students WHERE student_id=?";
            PreparedStatement ps2 = con.prepareStatement(deleteStudent);
            ps2.setInt(1, id);

            int rows = ps2.executeUpdate();

            if (rows > 0)
                System.out.println("Student deleted!");
            else
                System.out.println("Student not found!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}