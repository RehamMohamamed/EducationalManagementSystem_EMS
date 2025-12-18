package database.DAO;

import database.DBConnection;
import educational.Student;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    // 1. Register: حفظ طالب جديد
    public static boolean register(String firstName, String lastName, String email, String username, String password) {
        String query = "INSERT INTO Students (first_name, last_name, email, username, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, username);
            stmt.setString(5, password);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Login: التحقق من بيانات الطالب
    public static boolean login(String username, String password) {
        String query = "SELECT 1 FROM Students WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // لو لقينا صف واحد على الأقل، هيرجع true

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Register Course: ربط الطالب بكورس في جدول Student_course
    public static void registerInCourse(int studentId, int courseId) {
        String query = "INSERT INTO Student_course (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
            System.out.println("Course registered successfully.");
        } catch (SQLException e) {
            System.out.println("Course already registered or Error: " + e.getMessage());
        }
    }
}