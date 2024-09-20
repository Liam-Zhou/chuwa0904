package org.example.Factory;

// Concrete Product 2: Bike
public class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bike has started.");
    }
}
