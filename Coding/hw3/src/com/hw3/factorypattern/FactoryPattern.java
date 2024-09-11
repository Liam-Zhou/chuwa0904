package com.hw3.factorypattern;

public class FactoryPattern {
    public static void main(String[] args) {
        PersonFactory pf = new PersonFactory();
        Person p1 = pf.getPerson("Teacher");
        Person p2 = pf.getPerson("Engineer");
        Person p3 = pf.getPerson("Doctor");
        p1.saySomething();
        p2.saySomething();
        p3.saySomething();
    }
}
