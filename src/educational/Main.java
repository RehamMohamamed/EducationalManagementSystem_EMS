package educational;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Test

        // --- Create Doctors ---
        Doctor doc1 = new Doctor("ahmed", "123", 101, "Ahmed Mohamed", "ahmed@example.com");
        Doctor doc2 = new Doctor("sara", "456", 102, "Sara Ali", "sara@example.com");

        // --- Create Students ---
        Student s1 = new Student("mohamed", "pass1", 201, "Mohamed Tarek", "mohamed@example.com");
        Student s2 = new Student("laila", "pass2", 202, "Laila Hassan", "laila@example.com");
        Student s3 = new Student("omar", "pass3", 203, "Omar Khaled", "omar@example.com");

        // --- Create Courses ---
        Course c1 = new Course("Java Programming", "CS101", doc1, Level.ONE);
        Course c2 = new Course("Data Structures", "CS102", doc2, Level.TWO);

        // --- Register Students to Courses ---
        s1.registerCourse(c1);
        s2.registerCourse(c1);
        s2.registerCourse(c2);
        s3.registerCourse(c2);

        // --- Create Assignments ---
        doc1.createAssignment("Assignment 1", "A101", "Java Basics", 100, c1);
        doc2.createAssignment("Assignment 2", "A102", "Linked Lists", 100, c2);

        // --- Students submit solutions ---
        s1.submitAssignment(c1.getAssignments().get(0), "Solution by Mohamed");
        s2.submitAssignment(c1.getAssignments().get(0), "Solution by Laila");
        s2.submitAssignment(c2.getAssignments().get(0), "Solution by Laila for DS");
        s3.submitAssignment(c2.getAssignments().get(0), "Solution by Omar");

        // --- Doctor grades assignments ---
        doc1.gradeAssignment(s1, c1.getAssignments().get(0), 95);
        doc1.gradeAssignment(s2, c1.getAssignments().get(0), 88);
        doc2.gradeAssignment(s2, c2.getAssignments().get(0), 90);
        doc2.gradeAssignment(s3, c2.getAssignments().get(0), 85);

        // --- Display Courses & Students ---
        System.out.println("=== Courses ===");
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("\n=== Students' Grades ===");
        s1.viewGrades();
        s2.viewGrades();
        s3.viewGrades();

        System.out.println("\n=== Students' Submitted Solutions ===");
        s1.viewSolutions();
        s2.viewSolutions();
        s3.viewSolutions();

    }

}