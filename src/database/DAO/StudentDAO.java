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
        String sqlStudent = "SELECT * FROM Students WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlStudent)) {

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
                student.setPassword(rs.getString("password"));


                // جلب الكورسات المسجلة للطالب
                String sqlCourses = "SELECT c.* FROM Courses c " +
                        "JOIN Student_course sc ON c.course_id = sc.course_id " +
                        "WHERE sc.student_id = ?";
                try (PreparedStatement courseStmt = conn.prepareStatement(sqlCourses)) {
                    courseStmt.setInt(1, student.getUserID());
                    ResultSet courseRs = courseStmt.executeQuery();
                    while (courseRs.next()) {
                        Course c = new Course();
                        c.setCourseId(courseRs.getInt("course_id"));
                        c.setCourseName(courseRs.getString("course_name"));
                        c.setCourseCode(courseRs.getString("course_code"));
                        student.getRegisteredCourses().add(c);

                        // جلب الواجبات لكل كورس
                        String sqlAssignments = "SELECT * FROM Assignments WHERE courseID = ?";
                        try (PreparedStatement assignStmt = conn.prepareStatement(sqlAssignments)) {
                            assignStmt.setInt(1, c.getCourseId());
                            ResultSet assignRs = assignStmt.executeQuery();
                            while (assignRs.next()) {
                                Assignment a = new Assignment(
                                        assignRs.getString("title"),
                                        assignRs.getInt("assignment_id"),
                                        assignRs.getString("descri_ption"),
                                        assignRs.getFloat("max_degree"),
                                        c
                                );
                                c.addAssignment(a);
                                student.getAllAssignments().add(a);
                            }
                        }
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
}
