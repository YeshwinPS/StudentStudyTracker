package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/study_tracker";
            String user = "root";
            String password = "lily123";

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
