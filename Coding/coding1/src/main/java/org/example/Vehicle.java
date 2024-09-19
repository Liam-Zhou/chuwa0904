package org.example;

public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleSize size;

    public Vehicle(String licensePlate, VehicleSize size) {
        this.licensePlate = licensePlate;
        this.size = size;
    }

    public VehicleSize getSize() {
        return size;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public abstract boolean canFitInSpot(ParkingSpot spot); // Vehicle-specific logic to check if it fits
}

class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleSize.MOTORCYCLE);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        // A motorcycle can fit in any spot (compact, regular, large)
        return true;
    }
}

class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleSize.REGULAR);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        // A car can fit in a regular or large spot
        return spot.getSize() == VehicleSize.REGULAR || spot.getSize() == VehicleSize.LARGE;
    }
}

class Bus extends Vehicle {
    public Bus(String licensePlate) {
        super(licensePlate, VehicleSize.LARGE);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        // A bus needs a large spot or multiple adjacent regular spots
        return spot.getSize() == VehicleSize.LARGE;
    }
}
