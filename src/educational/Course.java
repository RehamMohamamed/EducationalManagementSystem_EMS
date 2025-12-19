package educational;

public class Course {

    private int courseId;
    private String courseName;
    private String courseCode;

    public Course() {}

    public Course(int id, String name, String code) {
        this.courseId = id;
        this.courseName = name;
        this.courseCode = code;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return courseName + " (" + courseCode + ")";
    }
}
