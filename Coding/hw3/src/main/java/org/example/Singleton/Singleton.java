package org.example.Singleton;

public class Singleton {
    // Volatile keyword ensures that multiple threads handle the singleton instance correctly
    private static volatile Singleton instance;

    // Private constructor to prevent instantiation from other classes
    private Singleton() {
        // Prevent instantiation from reflection
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    // Public method to provide global access to the instance
    public static Singleton getInstance() {
        if (instance == null) {  // First check (without synchronization)
            synchronized (Singleton.class) {
                if (instance == null) {  // Second check (with synchronization)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate functionality
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}