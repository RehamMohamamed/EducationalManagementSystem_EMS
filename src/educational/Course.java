package educational;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private int courseId;
    private String courseName;
    private String courseCode;
    private Doctor doctor;

    private List<Assignment> assignments = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public Course() {}

    public Course(String courseName, String courseCode, Doctor doctor) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.doctor = doctor;
    }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public List<Assignment> getAssignments() { return assignments; }
    public List<Student> getStudents() { return students; }

    public void addStudent(Student student) { students.add(student); }
    public void removeStudent(Student student) { students.remove(student); }
    public void addAssignment(Assignment assignment) { assignments.add(assignment); }


    @Override
    public String toString() {
        return "Course: " + courseName + " (" + courseCode + ")";
    }
}
