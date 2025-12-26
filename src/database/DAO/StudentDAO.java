package database.DAO;


import static database.CourseDAO.isStudentRegistered;
import educational.Course;
import educational.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            System.out.println(e.getMessage());
        }
        return null;
    }
        // to register for course and cheeck if you register to it before
    public static boolean registerInCourse(int studentId, int courseId) {

        //check if the student signed before this time ---> method used to check exist in courseDAO
        if (isStudentRegistered(studentId, courseId)) {
            JOptionPane.showMessageDialog(null, "You are already registered in this course");
            return false;
        }

        String sql = "INSERT INTO Student_course (student_id, course_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully registered in the course!");
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Registration failed due to database error!");
            return false;
        }
    }
    
    //fetch registered courses
    public static List<Course> getRegisteredCourses(int studentId) {
        List<Course> registeredcourses = new ArrayList<>();
        String sql = """
            SELECT c.course_id, c.course_name, c.course_code
            FROM Courses c
            JOIN Student_course sc ON c.course_id = sc.course_id
            WHERE sc.student_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course c = new Course(
                    rs.getInt("course_id"),
                    rs.getString("course_name"),
                    rs.getString("course_code")
                );
                registeredcourses.add(c);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return registeredcourses;
    }
    
    // fetch courses that i registered in it to select one from them and see its assignments
    public static List<String> getCourseNamesForStudent(int studentId) {
        List<String> courseNames = new ArrayList<>();
        String sql = """
            SELECT c.course_name
            FROM Courses c
            JOIN Student_course sc ON c.course_id = sc.course_id
            WHERE sc.student_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                courseNames.add(rs.getString("course_name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return courseNames;
    }
    
    //fetch students that registered in any course doctor created
    public static List<String> getStudentsInDoctorCourses(int doctorId) {
        List<String> studentNames = new ArrayList<>();
        String sql = """
            SELECT DISTINCT s.username
            FROM Students s
            JOIN Student_course sc ON s.student_id = sc.student_id
            JOIN Courses c ON sc.course_id = c.course_id
            WHERE c.doc_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                studentNames.add(rs.getString("username"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return studentNames;
    }
    
    //fetch courses belong to this doctor and the student registerd in it
    public static List<String> getDoctorCoursesForStudent(int doctorId, int studentId) {
        List<String> courseNames = new ArrayList<>();
        String sql = """
            SELECT DISTINCT c.course_name
            FROM Courses c
            JOIN Student_course sc ON c.course_id = sc.course_id
            WHERE c.doc_id = ? AND sc.student_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setInt(2, studentId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courseNames.add(rs.getString("course_name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return courseNames;
    }

    //get student id from his name to use it in correctAssignment
    public static int getStudentIdByName(String name) {
        String sql = "SELECT student_id FROM Students WHERE username = ?"; // أو أي عمود اسم الطالب عندك
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("student_id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // لو مش لاقى الطالب أو حصل خطأ
        return 0; 
    }
        
}
