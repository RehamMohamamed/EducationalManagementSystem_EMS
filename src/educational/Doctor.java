package educational;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {

    private List<Course> teachingCourses = new ArrayList<>();
    private List<Assignment> assignments = new ArrayList<>();

    public Doctor() {}

    public Doctor(String userName, String password,
                  String firstName, String lastName, String email) {
        super(userName, password, firstName, lastName, email);
    }

    public List<Course> getTeachingCourses() {
        return teachingCourses;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void addCourse(Course course) {
        teachingCourses.add(course);
    }
    public void createAssignment(String assignmentName , int assignmentID , String description , float maxGrade , Course course) {
        Assignment a = new Assignment(assignmentName, assignmentID, description, maxGrade , course);
        assignments.add(a);
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
        return "Doctor: " + getFullName() + " | Email: " + getEmail();
    }
}
