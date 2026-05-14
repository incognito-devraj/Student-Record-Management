package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "NewPassword123";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            initializeDatabase();
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(
                "DB Connection failed for user '" + USER + "' on '" + URL
                    + "'. Check that your MySQL password matches the value in DBConnection.java. MySQL said: "
                    + e.getMessage(),
                e
            );
        } catch (Exception e) {
            throw new RuntimeException("DB Connection failed: " + e.getMessage());
        }
    }

    private static void initializeDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students ("
                    + "id INT PRIMARY KEY, "
                    + "name VARCHAR(50) NOT NULL, "
                    + "age INT NOT NULL, "
                    + "course VARCHAR(50) NOT NULL)"
            );
        }
    }
}
