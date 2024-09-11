public class GarbageCollectionExample {
    public static void main(String[] args) {
        // Creating a new object of type Person
        Person2 person1 = new Person2("Alice");

        // person1 is no longer pointing to the Person object, so the object is eligible for GC
        person1 = null;

        // Creating another object
        Person2 person2 = new Person2("Bob");

        // Now person2 is pointing to a new Person object, so the previous object is eligible for GC
        person2 = new Person2("Charlie");

        // Suggesting GC (not guaranteed to happen immediately)
        System.gc();
    }
}
class Person2 {
    String name;

    Person2(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Garbage collected for " + name);
    }
}
