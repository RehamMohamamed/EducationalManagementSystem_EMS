package database.DAO;

import educational.Course;
import educational.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection connection;
    public CourseDAO(Connection connection) { this.connection = connection; }

    public boolean addCourse(Course course) {
        String sql = "INSERT INTO Courses (course_code,course_name,doc_id) VALUES (?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            if (course.getDoctor() != null) stmt.setInt(3, course.getDoctor().getUserID());
            else stmt.setNull(3, Types.INTEGER);
            int rows = stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) course.setCourseId(rs.getInt(1));
            }

            return rows > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getInt("course_id"));
                c.setCourseCode(rs.getString("course_code"));
                c.setCourseName(rs.getString("course_name"));
                int docId = rs.getInt("doc_id");
                if (!rs.wasNull()) {
                    DoctorDAO dao = new DoctorDAO(connection);
                    c.setDoctor(dao.getDoctorById(docId));
                }
                courses.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return courses;
    }
}
