package database;
import java.sql.*;
public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Student_System;encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
