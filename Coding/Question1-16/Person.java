public class Person {
    public void display(){
        System.out.println("Display person..");
    }
    //Static methods: These belong to the class, not instances, so they can be called without creating an object of the class.
    static void display(Person p){
        p.display();
    }

}
