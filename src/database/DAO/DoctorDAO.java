package database.DAO;

import educational.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    private Connection connection;
    public DoctorDAO(Connection connection) { this.connection = connection; }

    public boolean addDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctors (username,email,first_name,last_name) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, doctor.getUserName());
            stmt.setString(2, doctor.getEmail());
            stmt.setString(3, doctor.getfName());
            stmt.setString(4, doctor.getlName());
            int rows = stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) doctor.setUserID(rs.getInt(1));
            }
            return rows > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM Doctors WHERE doctor_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Doctor d = new Doctor();
                    d.setUserID(rs.getInt("doctor_id"));
                    d.setUserName(rs.getString("username"));
                    d.setEmail(rs.getString("email"));
                    d.setfName(rs.getString("first_name"));
                    d.setlName(rs.getString("last_name"));
                    return d;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setUserID(rs.getInt("doctor_id"));
                d.setUserName(rs.getString("username"));
                d.setEmail(rs.getString("email"));
                d.setfName(rs.getString("first_name"));
                d.setlName(rs.getString("last_name"));
                doctors.add(d);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return doctors;
    }
}
