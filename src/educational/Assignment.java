package educational;

import database.DAO.AssignmentDAO;

import java.awt.geom.Arc2D;
import java.util.HashMap;

public class Assignment{
    private String assignmentTitle;
    private String assignmentID;
    private String assignmentDescription;
    private float maxGrade;
    private float studentGrade;
//    private String studentSolution = "";
    private HashMap <Integer , String> solutions = new HashMap<>();
    private HashMap <Integer , Float> grades = new HashMap<>();
    private String correctSolution;
    private Course course;

    public Assignment(){}
    public Assignment(String assignmentTitle, String assignmentID, String assignmentDescription, float maxGrade , Course course){
        this.assignmentTitle = assignmentTitle;
        this.assignmentID = assignmentID;
        this.assignmentDescription = assignmentDescription;
        this.maxGrade = maxGrade;
        this.course = course;
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

    public Course getCourse() {
        return course;
    }

    // set grade
    public void setStudentGrade(float studentGrade) {
        this.studentGrade = studentGrade;
    }

//    public String getStudentSolution() {
//        return studentSolution;
//    }
//    public void setStudentSolution(String studentSolution) {
//        this.studentSolution = studentSolution;
//    }

    public String getCorrectSolution() {
        return correctSolution;
    }
    public void setCorrectSolution(String correctSolution) {
        this.correctSolution = correctSolution;
    }

    //methods

//    public void addSolution(String solution , Student student) {
//        this.studentSolution += solution;
//    }

    public void addSolution(Student student, String solution) {
        if(!solutions.containsKey(student.getUserID())){
            AssignmentDAO.saveSolution(student.getUserID(), assignmentID, solution);

        }
        else{
            System.out.println("You already submitted the assignment!");
        }
    }

    public String getSolution(Student student) {
        return AssignmentDAO.getSolution(student.getUserID(), assignmentID);
    }

    public void setGrade(Student student, float grade) {
        grades.put(student.getUserID(), grade);
    }

    public Float getGrade(Student student) {
        return grades.get(student.getUserID());
    }


    @Override
    public String toString() {
        return "educational.Assignment Title: " + assignmentTitle + ", Asssignement ID: " + assignmentID + ", educational.Assignment description: " + assignmentDescription;
    }
}
