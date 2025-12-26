package database.DAO;

import database.DBConnection;
import educational.Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;

public class AssignmentDAO {

   
    // submit solution
    public static boolean submitAssignmentAnswer(int studentId, int assignmentId, String answer) {

    String sql = """
        INSERT INTO Student_assignment (student_id, assignment_id, answer)
        VALUES (?, ?, ?)
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, studentId);
        ps.setInt(2, assignmentId);
        ps.setString(3, answer);

        ps.executeUpdate();
        return true;

    } catch (SQLException e) {
        // هنا غالبًا السبب إن الطالب سلّم قبل كده
        return false;
    }
}



    // check if submitted
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
            System.out.println(e.getMessage());
        }
        return false;
    }

    
    //get assignments + grades
    public static ArrayList<Assignment> getAssignmentsForStudent(int studentId) {

        ArrayList<Assignment> assignments = new ArrayList<>();

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

                //BigDecimal → Float
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

                assignments.add(assignment);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return assignments;
    }

 
    //Set Grade
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
            System.out.println(e.getMessage());
        }
    }
    
    // get max grade to make validation
    public static float getMaxGrade(int assignmentId) {
        String sql = "SELECT max_degree FROM Assignments WHERE assignment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, assignmentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getFloat("max_degree");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    // create assignment to specific doctor
    public static boolean createAssignment(String title, String description, float maxGrade, int courseId) {
        String sql = "INSERT INTO Assignments (title, descri_ption, max_degree, courseID) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setFloat(3, maxGrade);
            ps.setInt(4, courseId);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
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
            System.out.println(e.getMessage());
        }

        return null; // لو الطالب ما سلّمش
    }

    //delete assignmnet from doctor and student systems
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
            System.out.println(e.getMessage());
        }

        return false;
    }
    
    
    //----------------------------------------------
    
    //get assignment id when you choice title to use it to fetch the question more better than title BEC may be there are duplication
    public static int getAssignmentIdByTitle(String assignmentTitle, int courseId) {

        String sql = """
            SELECT assignment_id
            FROM Assignments
            WHERE title = ?
              AND courseID = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, assignmentTitle);
            ps.setInt(2, courseId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("assignment_id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // في حالة وجود مشكلة (مش المفروض تحصل)
        return 0;
    }
    
    
    // fetch assignmet question to answer it
    public static String getAssignmentDescription(int assignmentId, int courseId) {
        String sql = "SELECT descri_ption FROM Assignments WHERE assignment_id = ? AND courseID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, assignmentId);
            ps.setInt(2, courseId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("descri_ption");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ""; // لو مش موجود
    }
    
    //get assignment that specific doctor created
    public static ArrayList<Assignment> getAssignmentsByDoctor(int doctorId) {

        ArrayList<Assignment> assignments = new ArrayList<>();

            String sql = """
                SELECT a.assignment_id,
                       a.title,
                       a.descri_ption,
                       a.max_degree,
                       a.courseID
                FROM Assignments a
                JOIN Courses c ON a.courseID = c.course_id
                WHERE c.doc_id = ?
            """;

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, doctorId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Assignment assignment = new Assignment(
                            rs.getString("title"),
                            rs.getInt("assignment_id"),
                            rs.getString("descri_ption"),
                            rs.getFloat("max_degree"),
                            rs.getInt("courseID")
                    );

                    assignments.add(assignment);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return assignments;
        }

        //fetch assignments that doctor created to remove from it

        public static List<String> getAssignmentTitlesByDoctor(int doctorId) {
            List<String> titles = new ArrayList<>();

            String sql = """
                SELECT a.title
                FROM Assignments a
                JOIN Courses c ON a.courseID = c.course_id
                WHERE c.doc_id = ?
                ORDER BY a.title
            """;

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, doctorId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    titles.add(rs.getString("title"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return titles;
    }
        
    // get assignments titles that has no grade (not corrected yet)
        public static List<String> getPendingAssignmentTitles(int studentId, int courseId) {
        List<String> titles = new ArrayList<>();

        String sql = """
            SELECT a.title
            FROM Assignments a
            LEFT JOIN Student_assignment sa
                ON a.assignment_id = sa.assignment_id
                AND sa.student_id = ?
            WHERE a.courseID = ?
            AND (sa.grade IS NULL)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                titles.add(rs.getString("title"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titles;
    }
        
    // check if there is assignments in the course or not
        public static boolean hasAssignmentsInCourse(int courseId) {
            String sql = "SELECT 1 FROM Assignments WHERE courseID = ?";

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, courseId);
                ResultSet rs = ps.executeQuery();

                return rs.next(); // لو فيه نتيجة → فيه assignment
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false; // لو حصل خطأ أو ما فيش assignments
        }

}

