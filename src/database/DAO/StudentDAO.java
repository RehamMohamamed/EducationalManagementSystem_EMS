package database.DAO;

import database.DBConnection;
import educational.Student;
import educational.Course;
import educational.Assignment;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    // Login: التحقق من بيانات الطالب وجلب الكورسات والواجبات
    public static Student login(String username, String password) {

        String sql = "SELECT * FROM Students WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Student student = new Student();
                student.setUserID(rs.getInt("student_id"));
                student.setUserName(rs.getString("username"));
                student.setfName(rs.getString("first_name"));
                student.setlName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));

                // الكورسات فقط
                String sqlCourses =
                        "SELECT c.* FROM Courses c " +
                                "JOIN Student_course sc ON c.course_id = sc.course_id " +
                                "WHERE sc.student_id = ?";

                try (PreparedStatement cs = conn.prepareStatement(sqlCourses)) {
                    cs.setInt(1, student.getUserID());
                    ResultSet crs = cs.executeQuery();

                    while (crs.next()) {
                        Course c = new Course();
                        c.setCourseId(crs.getInt("course_id"));
                        c.setCourseName(crs.getString("course_name"));
                        c.setCourseCode(crs.getString("course_code"));
                        student.getRegisteredCourses().add(c);
                    }
                }

                return student;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Register Course: ربط الطالب بكورس في جدول Student_course
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

    //getID
    public static int getStudentId(String username) {
        String query = "SELECT student_id FROM Students WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("student_id"); // لو لقاه هيرجع الرقم
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // لو ملقاش الـ username هيرجع -1
    }
}
