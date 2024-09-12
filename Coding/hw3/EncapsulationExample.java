class Person {
   // Private fields (Encapsulated data)
   private String name;
   private int age;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }
}

public class EncapsulationExample {
   public static void main(String[] args) {
      Person person = new Person();

      person.setName("Lily");
      person.setAge(29);

      System.out.println("Name is " +person.getName());
      System.out.println("Age is " + person.getAge());
   }
}