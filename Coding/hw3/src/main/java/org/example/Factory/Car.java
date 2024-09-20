package org.example.Factory;

// Concrete Product 1: Car
public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car has started.");
    }
}

