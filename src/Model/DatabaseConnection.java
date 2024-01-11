package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://192.70.36.54:3306/saes3-aratovo", "saes3-aratovo", "Projet_rma_s3");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}