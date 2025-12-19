package educational;

import database.DAO.AssignmentDAO;
import database.DAO.CourseDAO;
import database.DAO.DoctorDAO;

public class Doctor extends User {

    public Doctor() {}

    @Override
    public boolean login(String username, String password) {

        Doctor d = DoctorDAO.login(username, password);
        if (d == null) return false;

        this.userID = d.userID;
        this.userName = d.userName;
        this.fName = d.fName;
        this.lName = d.lName;
        this.email = d.email;

        return true;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    public void gradeAssignment(Student student, Assignment assignment, float grade) {

        if (grade < 0 || grade > assignment.getMaxGrade()) {
            System.out.println("Invalid grade");
            return;
        }

        AssignmentDAO.setGrade(
                student.getUserID(),
                assignment.getAssignmentID(),
                grade
        );

        System.out.println("Grade added successfully!");
    }
    public void createCourse(String courseName, String courseCode) {

        boolean success = CourseDAO.createCourse(
                courseName,
                courseCode,
                this.getUserID()
        );

        if (success) {
            System.out.println("Course created successfully!");
        } else {
            System.out.println("Failed to create course!");
        }
    }
}
