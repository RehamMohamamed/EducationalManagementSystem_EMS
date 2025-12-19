package educational;

import database.DAO.DoctorDAO;
import database.DAO.StudentDAO;
import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Course> registeredCourses = new ArrayList<>();
    private ArrayList<Assignment> allAssignments = new ArrayList<>();
    private ArrayList<Assignment> submittedAssignments = new ArrayList<>();

    // constructors
    public Student() {}

    public Student(String userName, String password, int userID, String fName, String lName, String email) {
        super(userName, password, fName, lName, email);
    }

    // getters
    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public ArrayList<Assignment> getAllAssignments() {
        return allAssignments;
    }

    public ArrayList<Assignment> getSubmittedAssignments() {
        return new ArrayList<>(submittedAssignments);
    }

    // methods
    public void registerCourse(Course course) {
        if (course != null) {
            if (!registeredCourses.contains(course)) {
                StudentDAO.registerInCourse(this.getUserID(), course.getCourseId());
                registeredCourses.add(course);

                course.addStudent(this);
                allAssignments.clear();
                for (Course c : registeredCourses) {
                    allAssignments.addAll(c.getAssignments());
                }
            } else {
                System.out.println("Course already registered");
            }
        } else {
            System.out.println("Course is invalid");
        }
    }

    public void submitAssignment(Assignment assignment, String solution) {
        if (!registeredCourses.contains(assignment.getCourse())) {
            System.out.println("You are not registered in this course");
            return;
        }

        if (submittedAssignments.contains(assignment)) {
            System.out.println("You already submitted this assignment!");
            return;
        }

        submittedAssignments.add(assignment);
        assignment.addSolution(this, solution);

        System.out.println("Solution submitted successfully!");
    }

    // view registered courses
    public void viewRegisterdCourses() {
        if (!registeredCourses.isEmpty()) {
            for (Course course : registeredCourses) {
                System.out.println(course);
                System.out.println("====================================================================");
            }
        } else {
            System.out.println("Course list is empty");
        }
    }

    public void viewAllAssignments() {
        if (!allAssignments.isEmpty()) {
            for (Assignment assignment : allAssignments) {
                System.out.println(assignment);
            }
        } else {
            System.out.println("There are no assignments yet!");
        }
    }

    public void viewGrades() {
        if (submittedAssignments.isEmpty()) {
            System.out.println("No submitted assignments yet!");
            return;
        }

        System.out.println("===== Your Grades =====");
        for (Assignment assignment : submittedAssignments) {
            Float grade = assignment.getGrade(this);
            System.out.println("Assignment: " + assignment.getAssignmentTitle());
            if (grade == null)
                System.out.println("Grade: Not graded yet.");
            else
                System.out.println("Grade: " + grade + " / " + assignment.getMaxGrade());
            System.out.println("------------------------------------");
        }
    }

    // Login
    @Override
    public boolean login(String username , String password) {
        Student s = StudentDAO.login(username , password);
        return s != null;
    }

    // Sign Up

//    public void signUp(String username, String password, String fName, String lName, String email) {
//        StudentDAO.register(fName, lName, email, username, password);
//    }

    @Override
    public String toString() {
        return "Student: " + getFullName() +
               " Id: " + userID +
               " Email: " + email;
    }
}
