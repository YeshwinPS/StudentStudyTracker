package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateStudent {

    public static void updateStudent(int id, String name, int semester) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE students SET name=?, semester=? WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, semester);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Student updated!");
            else
                System.out.println("Student not found!");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}