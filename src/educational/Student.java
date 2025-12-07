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
                for(Course c : registeredCourses) {
                    allAssignments.addAll(c.getAssignments());
                }
            }
            else {
                System.out.println("Course already registered");
            }
        }
        else {
            System.out.println("Course is invalid");
        }
    }


    public void submitAssignment(Assignment assignment, String solution) {
        if(!registeredCourses.contains(assignment.getCourse())){
            System.out.println("You are not registered in this course");
            return;
        }
        // Check if student already submitted
        if (submittedAssignments.contains(assignment)) {
            System.out.println("You already submitted this assignment!");
            return;
        }

        // store assignment
        submittedAssignments.add(assignment);

        // send solution to assignment
        assignment.addSolution(this, solution);

        System.out.println("Solution submitted successfully!");
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



    public void viewSolutions() {

        if (submittedAssignments.isEmpty()) {
            System.out.println("No submitted assignments yet.");
            return;
        }

        for (Assignment assignment : submittedAssignments) {
            String solution = assignment.getSolution(this);
            System.out.println("Assignment: " + assignment.getAssignmentTitle());
            System.out.println("Your solution: " + solution);
            System.out.println("-------------------------------------------");
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



}
