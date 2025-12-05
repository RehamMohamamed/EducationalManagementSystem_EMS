package educational;
import educational.Assignment.* ;
import java.util.ArrayList;

public class Doctor extends User {
    // doctor's cousrse & assignments list
    private ArrayList <Course> teachingCourses = new ArrayList<>();
    private ArrayList <Assignment> assignments = new ArrayList<>();
    //constructors
    public Doctor() {}
    public Doctor(String userName, String password , String email , String fullName , String userID) {
        super(userName , password , email , fullName , userID);
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

    //unfinished yet
    public void createAssignment (String assignmentName , String assignmentID , String description , float maxGrade ) {}
//    Assignment a = new Assignment(assignmentName , assignmentID , description , maxGrade);

}
