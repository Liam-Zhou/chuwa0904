package org.example;

// Encapsulation: Class with private fields and public getter/setter methods
public class Vehiclee {
    private String brand;

    public Vehiclee(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void drive() {
        System.out.println(brand);
    }
}

// Inheritance: extends from parent class
class Carrr extends Vehiclee {
    private final int numberOfDoors;

    public Carrr(String brand, int numberOfDoors) {
        super(brand);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

// Polymorphism: Override
    @Override
    public void drive() {
        System.out.println(getBrand() + " car with " + numberOfDoors + " doors is driving at ");
    }
}