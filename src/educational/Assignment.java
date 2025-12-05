package educational;

public class Assignment{
    private String assignmentTitle;
    private String assignmentID;
    private String assignmentDescription;
    private float maxGrade;
    private float studentGrade;
    private String studentSolution;
    private String correctSolution;

    public Assignment(){}
    public Assignment(String assignmentTitle, String assignmentID, String assignmentDescription, float maxGrade){
        this.assignmentTitle = assignmentTitle;
        this.assignmentID = assignmentID;
        this.assignmentDescription = assignmentDescription;
        this.maxGrade = maxGrade;

    }

    // getters & setters
    public String getAssignmentTitle() {
        return assignmentTitle;
    }
    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }
    public String getAssignmentID() {
        return assignmentID;
    }
    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }
    public String getAssignmentDescription() {
        return assignmentDescription;
    }
    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }
    public float getMaxGrade() {
        return maxGrade;
    }
    public void setMaxGrade(float maxGrade) {
        this.maxGrade = maxGrade;
    }
    public float getStudentGrade() {
        return studentGrade;
    }

    // set grade
    public void setStudentGrade(float studentGrade) {
        this.studentGrade = studentGrade;
    }
    public String getStudentSolution() {
        return studentSolution;
    }
    public void setStudentSolution(String studentSolution) {
        this.studentSolution = studentSolution;
    }
    public String getCorrectSolution() {
        return correctSolution;
    }
    public void setCorrectSolution(String correctSolution) {
        this.correctSolution = correctSolution;
    }

    //methods
    public void addSolution(String solution , Student student) {
        this.studentSolution += solution;
    }

    @Override
    public String toString() {
        return "educational.Assignment Title: " + assignmentTitle + ", Asssignement ID: " + assignmentID + ", educational.Assignment description: " + assignmentDescription;
    }
}
