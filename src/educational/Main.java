package educational;
import database.DBConnection;
import database.DAO.DoctorDAO;
import educational.Doctor;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ نجيب الاتصال من DBConnection
        DBConnection.getConnection();

    }
}
