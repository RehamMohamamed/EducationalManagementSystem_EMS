import java.util.ArrayList;

public class Course {
    private ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    private ArrayList <Student> students = new ArrayList<>();
    private String courseName;
    private String courseCode;
    private Doctor doctor;

    public Course() {}
    public Course(String courseName, String courseCode, Doctor doctor) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.doctor = doctor;
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

    // methods

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    @Override
    public String toString () {
        return "Course name: " + courseName + ", Course code: " + courseCode;
    }

}
