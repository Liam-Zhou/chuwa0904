package com.hw3.polymorphism;

public class Polymorphism {
    public static void main(String[] args) {
        Person p1 = new Teacher("Kevin", 30);
        Person p2 = new Engineer("Kate", 40);
        // The reference of parent class can not access members of child class
        //System.out.println(p1.age);
        //p1.showInfo();
        Teacher t = (Teacher) p1;
        Engineer e = (Engineer) p2;
        t.showInfo();
        e.showInfo();
    }
}
