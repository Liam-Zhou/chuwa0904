package com.hw3.factorypattern;

public class Doctor implements Person{
    @Override
    public void saySomething() {
        System.out.println("I am a doctor.");
    }
}
