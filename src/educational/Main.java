package educational;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Test
       Doctor doctor = new Doctor("Ahmedabo7med" , "12354a" , 1234869 , "Ahmed Mohamed" , "abo7med22@gmail.com");
       Course course = new Course("Programing" , "CS112" ,doctor , Level.ONE);
       Student student = new Student("crisTarek12" , "123456kok" , 20245789 , "Cristian Tarek" , "crisTarek22@gmail.com");
       doctor.createAssignment("lec1" , "123" , "this is lec 1" , 10 , course);
       student.registerCourse(course);
       student.viewAllAssignments();

//

//       doctor.createAssignment("lec 1" , "101" , "assignment 1" , 100);
//       Assignment a = new Assignment("lec 1" , "101" , "assignment 1" , 100 , course);
//
//       student.submitAssignment(a , "solution 1");
//
//       Assignment a1 = new Assignment("lec 1" , "101" , "assignment 1" , 100);

      // student.submitAssignment(a1 , "solution 2");


    }

}