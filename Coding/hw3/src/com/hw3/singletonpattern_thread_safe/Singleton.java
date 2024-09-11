package com.hw3.singletonpattern_thread_safe;

public class Singleton {
    private String name;
    // volatile instance
    private static volatile Singleton instance;

    private Singleton(String name) {
        this.name = name;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton("Kevin");
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "name='" + name + '\'' +
                '}';
    }
}
