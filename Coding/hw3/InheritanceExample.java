// Parent class
class School {

   protected String schoolName = "Chuwa";
   public String performance() {
      return "Performs Great!";
   }
}

// Child class
class Student extends School {
   private String studentName = "Liam";
   public String getStudentName() {
      return studentName;
   }
}

// Usage
public class InheritanceExample {
   public static void main(String[] args) {
      School mySchool = new School();
      Student myStudent = new Student();

      System.out.println(myStudent.schoolName + " " +
            myStudent.getStudentName() + " " + myStudent.performance()); // Output: Chuwa Liam Performs Great!

   }
}