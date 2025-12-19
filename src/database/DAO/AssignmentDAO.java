package database.DAO;

import database.DBConnection;
import educational.Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;

public class AssignmentDAO {

    // ===============================
    // 1️⃣ Submit Solution
    // ===============================
    public static void saveSolution(int studentId, int assignmentId, String solution) {

        String sql = """
            INSERT INTO Student_assignment(student_id, assignment_id, answer)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, assignmentId);
            ps.setString(3, solution);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ===============================
    // 2️⃣ Check if Submitted
    // ===============================
    public static boolean isSubmitted(int studentId, int assignmentId) {

        String sql = """
            SELECT 1 FROM Student_assignment
            WHERE student_id = ? AND assignment_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, assignmentId);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===============================
    // 3️⃣ Get Assignments + Grades
    // ===============================
    public static ArrayList<Assignment> getAssignmentsForStudent(int studentId) {

        ArrayList<Assignment> list = new ArrayList<>();

        String sql = """
            SELECT a.assignment_id,
                   a.title,
                   a.descri_ption,
                   a.max_degree,
                   a.courseID,
                   sa.grade
            FROM Assignments a
            JOIN Student_course sc
                 ON a.courseID = sc.course_id
            LEFT JOIN Student_assignment sa
                 ON sa.assignment_id = a.assignment_id
                 AND sa.student_id = ?
            WHERE sc.student_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                // ✅ التحويل الصح من BigDecimal → Float
                Float grade = null;
                BigDecimal bd = rs.getBigDecimal("grade");
                if (bd != null) {
                    grade = bd.floatValue();
                }

                Assignment assignment = new Assignment(
                        rs.getString("title"),
                        rs.getInt("assignment_id"),
                        rs.getString("descri_ption"),
                        rs.getFloat("max_degree"),
                        grade,
                        rs.getInt("courseID")
                );

                list.add(assignment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ===============================
    // 4️⃣ Set Grade
    // ===============================
    public static void setGrade(int studentId, int assignmentId, float grade) {

        String sql = """
            UPDATE Student_assignment
            SET grade = ?
            WHERE student_id = ? AND assignment_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setFloat(1, grade);
            ps.setInt(2, studentId);
            ps.setInt(3, assignmentId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createAssignment(String title,
                                        String description,
                                        float maxGrade,
                                        int courseId) {

        String sql = """
        INSERT INTO Assignments (title, descri_ption, max_degree, courseID)
        VALUES (?, ?, ?, ?)
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setFloat(3, maxGrade);
            ps.setInt(4, courseId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getSolution(int studentId, int assignmentId) {

        String sql = """
        SELECT answer
        FROM Student_assignment
        WHERE student_id = ? AND assignment_id = ?
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, assignmentId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("answer");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // لو الطالب ما سلّمش
    }

    public static boolean removeAssignment(int assignmentId) {

        String deleteSubmissions = """
        DELETE FROM Student_assignment
        WHERE assignment_id = ?
    """;

        String deleteAssignment = """
        DELETE FROM Assignments
        WHERE assignment_id = ?
    """;

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false); // Transaction

            try (PreparedStatement ps1 = conn.prepareStatement(deleteSubmissions);
                 PreparedStatement ps2 = conn.prepareStatement(deleteAssignment)) {

                ps1.setInt(1, assignmentId);
                ps1.executeUpdate();

                ps2.setInt(1, assignmentId);
                int rows = ps2.executeUpdate();

                conn.commit();
                return rows > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



}
