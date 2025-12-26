/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;" +
                    "databaseName=Student_System;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";


    private static final String USER = "ems_user";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL , USER , PASSWORD);
//            System.out.println("Database connected successfully");
            return con;
        } catch (SQLException e) {
//            System.out.println("Database connection failed");
            
            System.out.println(e.getMessage());
            return null;
        }
    }
}
