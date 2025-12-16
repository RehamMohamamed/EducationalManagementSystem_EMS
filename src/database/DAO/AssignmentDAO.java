package database.DAO;

import database.DBConnection;
import java.sql.*;

public class AssignmentDAO {

    // save solution
    public static void saveSolution(int studentID, String assignmentID, String solution) {

        String sql = """
            INSERT INTO Assignment_Submissions(student_id, assignment_id, solution)
            VALUES (?, ?, ?)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentID);
            ps.setString(2, assignmentID);
            ps.setString(3, solution);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // get solution
    public static String getSolution(int studentID, String assignmentID) {

        String sql = """
            SELECT solution
            FROM Assignment_Submissions
            WHERE student_id = ? AND assignment_id = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentID);
            ps.setString(2, assignmentID);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("solution");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // set grade
    public static void setGrade(int studentID, String assignmentID, float grade) {

        String sql = """
            UPDATE Assignment_Submissions
            SET grade = ?
            WHERE student_id = ? AND assignment_id = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setFloat(1, grade);
            ps.setInt(2, studentID);
            ps.setString(3, assignmentID);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // get grade
    public static Float getGrade(int studentID, String assignmentID) {

        String sql = """
            SELECT grade
            FROM Assignment_Submissions
            WHERE student_id = ? AND assignment_id = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentID);
            ps.setString(2, assignmentID);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getFloat("grade");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
