package com.hw3.singletonpattern_thread_safe;

public class TestSingleton {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        System.out.println(s);
    }
}
