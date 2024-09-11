import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        // Create a HashSet
        HashSet<String> set = new HashSet<>();
        set.add("Alice");
        set.add("Bob");
        set.add("Charlie");

        // Trying to add a duplicate element
        set.add("Alice");  // Won't add as it's a duplicate

        // Display all elements
        for (String name : set) {
            System.out.println("Name: " + name);
        }
    }
}
