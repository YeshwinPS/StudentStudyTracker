package Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudyLog {

    public static void addLog(int hours, int stress) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO study_log (student_id, study_date, hours_studied, stress_level) " +
                         "VALUES ((SELECT MAX(student_id) FROM students), CURDATE(), ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hours);
            ps.setInt(2, stress);

            ps.executeUpdate();
            con.close();

            System.out.println("Study log added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
