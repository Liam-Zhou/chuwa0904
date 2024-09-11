class OuterClass {
    static class StaticNestedClass {
        void display() {
            System.out.println("This is a static nested class.");
        }
    }
}

public class StaticNestedClassTest {
    public static void main(String[] args) {
        // No need to create an instance of OuterClass
        OuterClass.StaticNestedClass nestedObj = new OuterClass.StaticNestedClass();
        nestedObj.display();  // Output: This is a static nested class.
    }
}
