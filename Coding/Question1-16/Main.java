public class Main {
    public static void main(String[] args) {
        Husband husband = new Husband();
         husband.setAge(18);
         System.out.println("Husband's Age: " + husband.getAge());

        // //static method, called w/o instance
         Person.display(husband);
        
        //run time polymorphsim:override
        husband.display();

        // compile time polymorphsim:overload
        Calculator cal = new Calculator();
        System.out.println(cal.add(1,2));//int
        System.out.println(cal.add(1.5,2.5));//double
        
    }
}
