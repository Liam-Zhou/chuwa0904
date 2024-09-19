package org.example.Singleton;

public class SingletonPatternDemo {
    public static void main(String[] args) {
        // Get the only instance of Singleton
        Singleton singletonInstance = Singleton.getInstance();

        // Call a method on the singleton instance
        singletonInstance.showMessage();  // Output: Hello from Singleton!
    }
}