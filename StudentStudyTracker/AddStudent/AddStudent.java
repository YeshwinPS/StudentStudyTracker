package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudent {

    public static void addStudent(String name, int semester) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO students (name, semester) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, semester);

            ps.executeUpdate();
            con.close();

            System.out.println("Student added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
