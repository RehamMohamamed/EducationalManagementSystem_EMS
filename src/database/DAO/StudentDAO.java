package database.DAO;

import database.DBConnection;
import educational.Student;
import educational.Course;
import java.sql.*;

public class StudentDAO {

    public static Student login(String username, String password) {

        String sql = """
        SELECT * FROM Students
        WHERE username = ? AND password = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Student s = new Student();
                s.setUserID(rs.getInt("student_id"));
                s.setUserName(rs.getString("username"));
                s.setfName(rs.getString("first_name"));
                s.setlName(rs.getString("last_name"));
                s.setEmail(rs.getString("email"));

                return s;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean registerInCourse(int studentId, int courseId) {

        String sql = """
        INSERT INTO Student_course (student_id, course_id)
        VALUES (?, ?)
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ You are already registered in this course");
            return false;
        }
    }

}
