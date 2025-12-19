package educational;

public class Assignment {

    private int assignmentID;
    private String assignmentTitle;
    private String assignmentDescription;
    private float maxGrade;
    private Float studentGrade;
    private int courseId;   // مهم جدًا

    public Assignment(String title, int id, String desc,
                      float maxGrade, Float grade, int courseId) {

        this.assignmentTitle = title;
        this.assignmentID = id;
        this.assignmentDescription = desc;
        this.maxGrade = maxGrade;
        this.studentGrade = grade;
        this.courseId = courseId;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public float getMaxGrade() {
        return maxGrade;
    }

    public Float getStudentGrade() {
        return studentGrade;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "Assignment: " + assignmentTitle;
    }
}
