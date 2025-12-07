package educational;

import java.util.ArrayList;

public class Student extends User {
    private ArrayList <Course> registeredCourses = new ArrayList<>();
    private ArrayList <Assignment> allAssignments = new ArrayList<>();
    public ArrayList <Assignment> submittedAssignments = new ArrayList<>();
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
                System.out.println("educational.Course already registered");
            }
        }
        else {
            System.out.println("educational.Course is invalid");
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
            System.out.println("educational.Course list is empty");
    }


    public void viewAllCourses (Level level) {
        System.out.println("Courses for level " + level + " : ");
            for(String course : level.getCourses()) {
                System.out.println("- " + course);
            }
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
        }else{
            System.out.println("No solutions");
        }
    }


    public void viewGrades () {
        if(submittedAssignments != null) {
            for (Assignment assignment : submittedAssignments) {
                System.out.println("The " + assignment.getCourse() + " grade is " + assignment.getStudentGrade());
            }
        }
        else{
            System.out.println("No solutions !");
        }

    }


}
