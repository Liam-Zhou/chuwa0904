 class StaticExample {
    static int count = 0;  // Static field, shared by all instances

    StaticExample() {
        count++;  // Incremented each time a new instance is created
    }

    void showCount() {
        System.out.println("Count: " + count);
    }
}
public class Test{
    public static void main(String[] args) {
        StaticExample obj1 = new StaticExample();
        StaticExample obj2 = new StaticExample();
        obj1.showCount();  // Output: Count: 2
        obj2.showCount();  // Output: Count: 2
    }
}
