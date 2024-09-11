package com.hw3.factorypattern;

public class Teacher implements Person{
    @Override
    public void saySomething() {
        System.out.println("I am a teacher.");
    }
}
