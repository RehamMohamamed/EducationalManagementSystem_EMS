package database.DAO;

import database.DBConnection;
import educational.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    
    //check if student registered before used with registerInCourse in studentDAO 
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
    
    //to fetch courses from database
    public static List<Course> viewAllCourses() {
            List<Course> courses = new ArrayList<>();
        String sql = "SELECT course_id, course_name, course_code FROM Courses";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Course c = new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getString("course_code")
                );
                courses.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    //to create course
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
            System.out.println("Course code already exists OR DB error");
            return false;
        }
    }
    
    // fetch all courses names only for ComboBox for registeration
    public static List<String> getAllCourseNames() {

        List<String> courseNames = new ArrayList<>();
        String sql = "SELECT course_name FROM Courses";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                courseNames.add(rs.getString("course_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseNames;
    }
    
    // get course_id from courses names when click on it from the combobox for registeration
    public static int getCourseIdByName(String courseName) {
        String sql = "SELECT course_id FROM Courses WHERE course_name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, courseName);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt("course_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // if there problem 
        return 0; 
    }
    
    //fetch course assignments to let the student choice one of them to answer it 
    public static List<String> getAssignmentTitlesForStudentAndCourse(int studentId, int courseId) {
        List<String> assignmentTitles = new ArrayList<>();
        String sql = """
            SELECT a.title
            FROM Assignments a
            JOIN Student_course sc ON a.courseID = sc.course_id
            WHERE sc.student_id = ? AND a.courseID = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                assignmentTitles.add(rs.getString("title"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return assignmentTitles;
    }

    
    //fetch courses that the doctor is responsible for it to set the assignment for the combobox
    public static List<String> getCoursesByDoctor(int doctorId) {
        List<String> courses = new ArrayList<>();
        String sql = "SELECT course_name FROM Courses WHERE doc_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                courses.add(rs.getString("course_name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return courses;
    }
    
    //to fetch the courses the doctor responsible for it to see it 
    public static List<Course> getDoctorCourses(int doctorId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT course_id, course_name, course_code FROM Courses WHERE doc_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course c = new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getString("course_code")
                );
                courses.add(c);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return courses;
    }


