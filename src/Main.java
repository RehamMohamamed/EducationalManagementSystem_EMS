//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Test
       Doctor doctor = new Doctor("Ahmedabo7med" , "12354a" , "abo7med22@gmail.com" , "Ahmed Mohamed" , "1234869");
       Course course = new Course("Programing" , "CS112" ,doctor );
       Student student = new Student("crisTarek12" , "123456kok" , "20245789" , "Cristian Tarek" , "crisTarek22@gmail.com");

       student.registerCourse(course);
       student.viewCourses();

    }
}