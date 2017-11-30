package celinederoland.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sql {

    private static Connection connect = null;

    public static Connection connect() {

        if(connect == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Sql.connect = DriverManager.getConnection("jdbc:mysql://greek_figures:3306", "root", "secret");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Sql.connect;
    }

}
