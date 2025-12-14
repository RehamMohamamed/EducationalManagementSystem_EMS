package database.DAO;
import database.DBConnection;
import java.sql.*;
import educational.Course;
import java.util.*;

public class CourseDAO {
    public void insertCourse(Course c) {
        String sql = "INSERT INTO Course (name, code, doctor_id) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getCourseName());
            ps.setString(2, c.getCourseCode());
            if (c.getDoctor() != null) ps.setInt(3, c.getDoctor().getUserID());
            else ps.setNull(3, Types.INTEGER);

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT id, name, code, doctor_id FROM Course";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Course c = new Course();
                c.setCourseName(rs.getString("name"));
                c.setCourseCode(rs.getString("code"));
                // لو عايز تجيب الدكتور مرتبط، هتعمل DoctorDAO.findById(rs.getInt("doctor_id"))
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
