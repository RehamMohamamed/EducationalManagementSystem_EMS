package educational;

import java.util.ArrayList;


public class Course {
    private ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    private ArrayList <Student> students = new ArrayList<>();
    private static ArrayList <Course> allCourses = new ArrayList<>();
    private String courseName;
    private String courseCode;
    private Doctor doctor;
    private Level level;

    public Course() {}
    public Course(String courseName, String courseCode, Doctor doctor , Level level) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.doctor = doctor;
        this.level = level;
        allCourses.add(this);
    }

    // getters & setters
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Level getLevel() {return level;}
    public void setLevel(Level level) {this.level = level;}
    public static ArrayList<Course> getAllCourses() {return allCourses;}
    // methods

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    @Override
    public String toString () {
        return "educational.Course name: " + courseName + ", educational.Course code: " + courseCode;
    }

}
