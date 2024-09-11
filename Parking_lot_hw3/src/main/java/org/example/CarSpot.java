package org.example;

public class CarSpot extends ParkingSpot {
    @Override
    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle instanceof Car;
    }
}
