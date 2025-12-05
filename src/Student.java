import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User {
    private ArrayList <Course> registeredCourses = new ArrayList<>();
    private ArrayList <Assignment> allAssignments = new ArrayList<>();
    private ArrayList <Assignment> submittedAssignments = new ArrayList<>();
    // constructors
    public Student () {}
    public Student (String userName , String password , String userID ,String fullName ,String email) {
        super(userName , password , userID , fullName , email);
    }
    //getters
    public ArrayList <Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public ArrayList<Assignment> getAllAssignments() {
        return allAssignments;
    }

    public ArrayList<Assignment> getSubmittedAssignments() {
        return submittedAssignments;
    }

    // methods
    public void registerCourse (Course course) {
        if(course != null) {
            if(!registeredCourses.contains(course)) {
                registeredCourses.add(course);
                course.addStudent(this);
            }
            else {
                System.out.println("Course already registered");
            }
        }
        else {
            System.out.println("Course is invalid");
        }
    }

    // submit solution
    public void submitAssignment (Assignment assignment , String solution) {
        submittedAssignments.add(assignment);
        assignment.addSolution(solution , this);
    }

    // view registered courses
    public void viewRegisterdCourses () {
        if(registeredCourses != null) {
            for (Course course : registeredCourses) {
                System.out.println(course.toString());
                System.out.println("====================================================================");
            }
        }
        else
            System.out.println("Course list is empty");
    }

    // مش عارف اشتغل فيها
    public void viewAllCourses () {

    }

    public void viewAllAssignments () {
        if(allAssignments != null) {
            for (Assignment assignment : allAssignments) {
                System.out.println(assignment.toString());
            }
        }
        else
            System.out.println("There are no assignments yet!");
    }


    // Implement this
    public void viewSolutions () {
        if(submittedAssignments != null) {
            for (Assignment assignment : submittedAssignments) {
                System.out.println(assignment.getStudentSolution());
            }
        }
    }

}
