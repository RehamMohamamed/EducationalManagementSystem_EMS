package database.DAO;

import database.DBConnection;
import java.sql.*;

public class CourseDAO {

    public static boolean isStudentRegistered(int studentId, int courseId) {

        String sql = """
        SELECT 1 FROM Student_course
        WHERE student_id = ? AND course_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void viewAllCourses() {

        String sql = "SELECT course_id, course_name, course_code FROM Courses";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("course_id") + " - " +
                                rs.getString("course_name") +
                                " (" + rs.getString("course_code") + ")"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean createCourse(String courseName,String courseCode, int doctorId) {

        String sql = """
        INSERT INTO Courses (course_name,course_code,  doc_id)
        VALUES (?, ?, ?)
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, courseName);
            ps.setString(2, courseCode);
            ps.setInt(3, doctorId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Course code already exists OR DB error");
            return false;
        }
    }


}
