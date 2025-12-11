package educational;
import educational.Assignment.* ;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends User {
    // doctor's cousrse & assignments list
    private ArrayList <Course> teachingCourses = new ArrayList<>();
    private ArrayList <Assignment> assignments = new ArrayList<>();
    //constructors
    public Doctor() {}
    public Doctor(String userName, String password ,int userID , String fullName , String email) {
        super(userName , password , userID , fullName , email);
    }

    // getters

    public ArrayList<Course> getTeachingCourses() {
        return teachingCourses;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }


    //methods
    public void createCoursse (String courseName , String courseID , Level level) {
        Course newCourse = new Course(courseName , courseID , this , level);
        teachingCourses.add(newCourse);
    }


    public void createAssignment (String assignmentName , String assignmentID , String description , float maxGrade , Course course) {
       Assignment a = new Assignment(assignmentName , assignmentID , description , maxGrade , course);
       this.assignments.add(a);
       course.addAssignment(a);
    }

    public void gradeAssignment(Student student, Assignment assignment , float grade) {



        // check if the student submitted
        String solution = assignment.getSolution(student);

        if (solution == null) {
            System.out.println("Student did not submit this assignment!");
            return;
        }

        // show the student's solution first
        System.out.println("----- Student Solution -----");
        System.out.println(solution);
        System.out.println("----------------------------");

        // validate grade
        if (grade < 0 || grade > assignment.getMaxGrade()) {
            System.out.println("Invalid grade! Must be between 0 and " + assignment.getMaxGrade());
            return;
        }


        assignment.setGrade(student, grade);

        System.out.println("Grade added successfully!");
    }



    @Override
    public String toString() {
        return "Doctor: " + fullName +
                "Id: " + userID +
                "Email: " + email;
    }




}
