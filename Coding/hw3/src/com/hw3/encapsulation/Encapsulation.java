package com.hw3.encapsulation;

public class Encapsulation {
    public static void main(String[] args) {
        Person p1 = new Person("Kevin", 10);
        // Using setter and getter to access attributes of Person class
        System.out.println(p1.getName() + " " + p1.getAge());
        System.out.println("After one year");
        p1.setAge(p1.getAge() + 1);
        System.out.println(p1.getName() + " " + p1.getAge());
    }
}
