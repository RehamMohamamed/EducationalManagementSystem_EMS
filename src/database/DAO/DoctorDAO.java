package database.DAO;

import database.DBConnection;
import educational.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    private Connection connection;

    public DoctorDAO(Connection connection) {
        this.connection = connection;
    }

    // إضافة دكتور جديد
    public boolean addDoctor(Doctor doctor, String password) {
        String sql = "INSERT INTO Doctors (username, email, first_name, last_name, password) VALUES (?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, doctor.getUserName());
            stmt.setString(2, doctor.getEmail());
            stmt.setString(3, doctor.getfName());
            stmt.setString(4, doctor.getlName());
            stmt.setString(5, password);

            int rows = stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) doctor.setUserID(rs.getInt(1));
            }
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // جلب دكتور بالـ ID
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
                    d.setPassword(rs.getString("password"));
                    return d;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // جلب كل الدكاترة
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
                d.setPassword(rs.getString("password"));
                doctors.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    // Login دكتور
    public static Doctor login(String username, String password) {
        String sql = "SELECT * FROM Doctors WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setUserID(rs.getInt("doctor_id"));
                doctor.setUserName(rs.getString("username"));
                doctor.setfName(rs.getString("first_name"));
                doctor.setlName(rs.getString("last_name"));
                doctor.setEmail(rs.getString("email"));
                doctor.setPassword(rs.getString("password"));
                return doctor;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
