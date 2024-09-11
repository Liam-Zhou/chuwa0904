package com.hw3.polymorphism;

public class Engineer extends Person{
    int age;
    public Engineer(String name, int age) {
        super(name);
        this.age = age;
    }
    public void showInfo() {
        System.out.println("I am " + this.name + ", " + this.age + " years old, and I am an engineer.");
    }
}
