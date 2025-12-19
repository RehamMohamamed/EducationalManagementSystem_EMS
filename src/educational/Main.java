package educational;

import database.DBConnection;
import database.DAO.StudentDAO;
import database.DAO.DoctorDAO;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ فتح الاتصال
        DBConnection.getConnection();

        // -----------------------------
        // 2️⃣ Login Student
        Student student = StudentDAO.login("reem12", "123"); // استخدمي بيانات موجودة في DB
        if (student != null) {
            System.out.println("✅ Welcome Student: " + student.getFullName());
        } else {
            System.out.println("❌ Student login failed");
            return; // لو فشل login نوقف البرنامج
        }

        // -----------------------------
        // 3️⃣ Login Doctor
        Doctor doctor = DoctorDAO.login("ahmed12", "1234"); // استخدمي بيانات موجودة في DB
        if (doctor != null) {
            System.out.println("✅ Welcome Doctor: " + doctor.getFullName());
        } else {
            System.out.println("❌ Doctor login failed");
            return;
        }

        // -----------------------------
        // 4️⃣ عرض كل كورسات الطالب
        System.out.println("\n===== Student Courses =====");
        student.viewRegisterdCourses();

        // -----------------------------
        // 5️⃣ عرض كل الواجبات للطالب
        System.out.println("\n===== Assignments =====");
        student.viewAllAssignments();

        // -----------------------------
        // 6️⃣ تقديم واجب إذا موجود
        if (!student.getAllAssignments().isEmpty()) {
            Assignment assignment = student.getAllAssignments().get(0);
            student.submitAssignment(assignment, "My solution");
            System.out.println("✅ Assignment submitted!");
        }

        // -----------------------------
        // 7️⃣ الدكتور يضع درجة إذا فيه واجب
        if (!student.getAllAssignments().isEmpty()) {
            Assignment assignment = student.getAllAssignments().get(0);
            doctor.gradeAssignment(student, assignment, 95); // مثال grade
            System.out.println("✅ Assignment graded by Doctor!");
        }

        // -----------------------------
        // 8️⃣ الطالب يشوف درجاته
        System.out.println("\n===== Student Grades =====");
        student.viewGrades();
    }
}
