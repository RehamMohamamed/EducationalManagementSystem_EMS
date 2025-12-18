package database.DAO;

import database.DBConnection;
import educational.Assignment;
import java.sql.*;
import java.util.ArrayList;

public class AssignmentDAO {

    // 1. Submit Solution: حفظ الإجابة في جدول Student_assignment
    public static void saveSolution(int studentId, String assignmentId, String solution) {
        // نستخدم MERGE أو نتحقق لو موجود نحدث، لو مش موجود ننشئ (هنا INSERT بسيط)
        String query = "INSERT INTO Student_assignment (student_id, assignment_id, answer) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, Integer.parseInt(assignmentId));
            stmt.setString(3, solution);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update existing solution logic here if needed.");
        }
    }

    // 2. Get Solution: جلب إجابة طالب معينة
    public static String getSolution(int studentId, String assignmentId) {
        String query = "SELECT answer FROM Student_assignment WHERE student_id = ? AND assignment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, Integer.parseInt(assignmentId));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("answer");
        } catch (SQLException e) { e.printStackTrace(); }
        return "No solution found.";
    }

    // 3. Get Grade: جلب الدرجة
    public static Float getGrade(int studentId, String assignmentId) {
        String query = "SELECT grade FROM Student_assignment WHERE student_id = ? AND assignment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, Integer.parseInt(assignmentId));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                float g = rs.getFloat("grade");
                return rs.wasNull() ? null : g;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // Student Class
    // 4. Get All Assignments for Student Courses: جلب كل الواجبات للكورسات اللي الطالب مسجل فيها
    public static ArrayList<Assignment> getAssignmentsForStudent(int studentId) {
        ArrayList<Assignment> list = new ArrayList<>();
        String query = "SELECT a.* FROM Assignments a " +
                "JOIN Student_course sc ON a.courseID = sc.course_id " +
                "WHERE sc.student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // ملاحظة: الـ Constructor بتاعك بيحتاج كائن Course، ممكن تبعت null أو تعدله
                Assignment asm = new Assignment(
                        rs.getString("title"),
                        String.valueOf(rs.getInt("assignment_id")),
                        rs.getString("descri_ption"),
                        rs.getFloat("max_degree"),
                        null
                );
                list.add(asm);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}