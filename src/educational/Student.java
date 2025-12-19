package educational;

import database.DAO.AssignmentDAO;
import database.DAO.CourseDAO;
import database.DAO.StudentDAO;
import java.util.ArrayList;

public class Student extends User {

    private ArrayList<Course> registeredCourses = new ArrayList<>();

    public Student() {}

    @Override
    public boolean login(String username, String password) {

        Student s = StudentDAO.login(username, password);
        if (s == null) return false;

        this.userID = s.userID;
        this.userName = s.userName;
        this.fName = s.fName;
        this.lName = s.lName;
        this.email = s.email;
        this.registeredCourses = s.registeredCourses;

        return true;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    public void submitAssignment(Assignment assignment, String solution) {

        int assignmentId = assignment.getAssignmentID();
        int courseId = assignment.getCourseId();

        if (!CourseDAO.isStudentRegistered(userID, courseId)) {
            System.out.println("You are not registered in this course");
            return;
        }

        if (AssignmentDAO.isSubmitted(userID, assignmentId)) {
            System.out.println("You already submitted this assignment!");
            return;
        }

        AssignmentDAO.saveSolution(userID, assignmentId, solution);
        System.out.println("Solution submitted successfully!");
    }

    public void viewAllAssignments() {

        var list = AssignmentDAO.getAssignmentsForStudent(userID);

        if (list.isEmpty()) {
            System.out.println("There are no assignments yet!");
            return;
        }

        for (Assignment a : list) {
            System.out.println(a);
        }
    }

    public void registerCourse(int courseId) {

        int studentId = this.getUserID();

        // check لو مسجل قبل كده
        if (CourseDAO.isStudentRegistered(studentId, courseId)) {
            System.out.println("You are already registered in this course!");
            return;
        }

        boolean success = StudentDAO.registerInCourse(studentId, courseId);

        if (success) {
            System.out.println("Course registered successfully!");
        }
    }


    public void viewGrades() {

        var list = AssignmentDAO.getAssignmentsForStudent(userID);

        System.out.println("===== Your Grades =====");

        for (Assignment a : list) {
            System.out.println("Assignment: " + a.getAssignmentTitle());

            if (a.getStudentGrade() == null)
                System.out.println("Grade: Not graded yet");
            else
                System.out.println("Grade: " + a.getStudentGrade()
                        + " / " + a.getMaxGrade());

            System.out.println("----------------------");
        }
    }
}
