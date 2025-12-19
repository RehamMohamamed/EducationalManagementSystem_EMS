package educational;
import educational.*;
import database.DAO.*;

import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== LMS SYSTEM =====");
            System.out.println("1. Student Login");
            System.out.println("2. Doctor Login");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1 -> studentDashboard();
                case 2 -> doctorDashboard();
                case 0 -> {
                    System.out.println("Bye 👋");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ==================================================
    // STUDENT DASHBOARD
    // ==================================================
    static void studentDashboard() {

        Student student = new Student();

        System.out.print("Username: ");
        String username = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();

        if (!student.login(username, password)) {
            System.out.println("❌ Login failed");
            return;
        }

        System.out.println("✅ Student logged in!");

        while (true) {
            System.out.println("\n===== STUDENT DASHBOARD =====");
            System.out.println("1. View all courses");
            System.out.println("2. Register in course");
            System.out.println("3. View all assignments");
            System.out.println("4. Submit assignment");
            System.out.println("5. View grades");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            int c = in.nextInt();
            in.nextLine();

            switch (c) {
                case 1 -> CourseDAO.viewAllCourses();

                case 2 -> {
                    System.out.print("Enter course ID: ");
                    int courseId = in.nextInt();
                    in.nextLine();
                    student.registerCourse(courseId);
                }

                case 3 -> student.viewAllAssignments();

                case 4 -> submitAssignmentMenu(student);

                case 5 -> student.viewGrades();

                case 0 -> {
                    System.out.println("Logged out 👋");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ==================================================
    // SUBMIT ASSIGNMENT (SHOW DESCRIPTION FIRST)
    // ==================================================
    static void submitAssignmentMenu(Student student) {

        var assignments = AssignmentDAO.getAssignmentsForStudent(student.getUserID());

        if (assignments.isEmpty()) {
            System.out.println("No assignments available!");
            return;
        }

        System.out.println("\nAvailable Assignments:");
        for (Assignment a : assignments) {
            System.out.println(
                    a.getAssignmentID() + " - " + a.getAssignmentTitle()
            );
        }

        System.out.print("Enter assignment ID: ");
        int aid = in.nextInt();
        in.nextLine();

        for (Assignment a : assignments) {
            if (a.getAssignmentID() == aid) {

                // ✅ عرض وصف السؤال قبل الحل
                System.out.println("\n===== Assignment Description =====");
                System.out.println(a.getAssignmentDescription());
                System.out.println("==================================");

                System.out.print("Enter your solution: ");
                String solution = in.nextLine();

                student.submitAssignment(a, solution);
                return;
            }
        }

        System.out.println("Assignment not found!");
    }

    // ==================================================
    // DOCTOR DASHBOARD
    // ==================================================
    static void doctorDashboard() {

        Doctor doctor = new Doctor();

        System.out.print("Username: ");
        String username = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();

        if (!doctor.login(username, password)) {
            System.out.println("❌ Login failed");
            return;
        }

        System.out.println("✅ Doctor logged in!");

        while (true) {
            System.out.println("\n===== DOCTOR DASHBOARD =====");
            System.out.println("1. View all courses");
            System.out.println("2. Create course");
            System.out.println("3. Create assignment");
            System.out.println("4. Grade assignment");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            int c = in.nextInt();
            in.nextLine();

            switch (c) {
                case 1 -> CourseDAO.viewAllCourses();

                case 2 -> createCourseMenu(doctor);

                case 3 -> createAssignmentMenu();

                case 4 -> gradeAssignmentMenu();

                case 0 -> {
                    System.out.println("Logged out 👋");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }

    // ==================================================
    // CREATE COURSE (DOCTOR)
    // ==================================================
    static void createCourseMenu(Doctor doctor) {

        System.out.print("Course name: ");
        String name = in.nextLine();

        System.out.print("Course code: ");
        String code = in.nextLine();

        doctor.createCourse(name, code);
    }

    // ==================================================
    // CREATE ASSIGNMENT (DOCTOR)
    // ==================================================
    static void createAssignmentMenu() {

        System.out.print("Course ID: ");
        int courseId = in.nextInt();
        in.nextLine();

        System.out.print("Assignment title: ");
        String title = in.nextLine();

        System.out.print("Description: ");
        String desc = in.nextLine();

        System.out.print("Max grade: ");
        float max = in.nextFloat();
        in.nextLine();

        AssignmentDAO.createAssignment(title, desc, max, courseId);
        System.out.println("Assignment created successfully!");
    }

    // ==================================================
    // GRADE ASSIGNMENT (SHOW SOLUTION FIRST)
    // ==================================================
    static void gradeAssignmentMenu() {

        System.out.print("Student ID: ");
        int studentId = in.nextInt();

        System.out.print("Assignment ID: ");
        int assignmentId = in.nextInt();
        in.nextLine();

        // ✅ عرض إجابة الطالب قبل التصحيح
        String solution = AssignmentDAO.getSolution(studentId, assignmentId);

        if (solution == null) {
            System.out.println("❌ Student did not submit this assignment!");
            return;
        }

        System.out.println("\n===== Student Answer =====");
        System.out.println(solution);
        System.out.println("==========================");

        System.out.print("Enter grade: ");
        float grade = in.nextFloat();
        in.nextLine();

        AssignmentDAO.setGrade(studentId, assignmentId, grade);
        System.out.println("✅ Grade added successfully!");
    }
}
