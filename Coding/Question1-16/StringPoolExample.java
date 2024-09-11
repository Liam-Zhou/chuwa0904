public class StringPoolExample {
    public static void main(String[] args) {
        // Using string literals, these will be stored in the String pool
        String str1 = "Hello";
        String str2 = "Hello";

        // Since both strings are the same literal, they point to the same object in the pool
        System.out.println(str1 == str2); // Output: true (same reference)

        // Creating a new string object using the 'new' keyword
        String str3 = new String("Hello");

        // Even though str3 has the same content, it doesn't point to the same object in the pool
        System.out.println(str1 == str3); // Output: false (different objects)

        // Interning str3, which forces it to refer to the string in the pool
        String str4 = str3.intern();
        System.out.println(str1 == str4); // Output: true (same reference now)
    }
}
