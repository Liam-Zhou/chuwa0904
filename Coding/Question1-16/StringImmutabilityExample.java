public class StringImmutabilityExample {
    public static void main(String[] args) {
        String original = "Hello";
        
        // Attempting to modify the string
        String modified = original.concat(", World!");

        // Original string remains unchanged
        System.out.println("Original: " + original);  // Output: Hello
        System.out.println("Modified: " + modified);  // Output: Hello, World!
    }
}
