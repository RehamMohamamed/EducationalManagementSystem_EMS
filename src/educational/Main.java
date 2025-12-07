package educational;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Test
//       Doctor doctor = new Doctor("Ahmedabo7med" , "12354a" , "abo7med22@gmail.com" , "Ahmed Mohamed" , "1234869");
//       Course course = new Course("Programing" , "CS112" ,doctor , Level.ONE);
//       Student student = new Student("crisTarek12" , "123456kok" , "20245789" , "Cristian Tarek" , "crisTarek22@gmail.com");
//

//       doctor.createAssignment("lec 1" , "101" , "assignment 1" , 100);
//       Assignment a = new Assignment("lec 1" , "101" , "assignment 1" , 100 , course);
//
//       student.submitAssignment(a , "solution 1");
//
//       Assignment a1 = new Assignment("lec 1" , "101" , "assignment 1" , 100);

      // student.submitAssignment(a1 , "solution 2");


//       student.viewSolutions();
        Doctor doctor = new Doctor("doc1", "123", "D001", "Dr Ahmed", "doc@gmail.com");

        // Student
        Student st = new Student("st1", "456", "S001", "Reham Mohamed", "reham@gmail.com");

        // Course
        Course c1 = new Course("OOP", "CS201", doctor, Level.TWO);

        // Assignment
        Assignment a1 = new Assignment("HW1", "A001", "Solve Q1 and Q2", 20, c1);
        Assignment a2 = new Assignment("HW1", "A001", "Solve Q1 and Q2", 20, c1);


        // Student submits solution
        st.submitAssignment(a2, "programming is fun");
        st.submitAssignment(a1, "logic is fun");

        // Doctor grades assignment
        doctor.gradeAssignment(st, a2);
        doctor.gradeAssignment(st, a1);


        // Student views grade
        st.viewGrades();
    }

}