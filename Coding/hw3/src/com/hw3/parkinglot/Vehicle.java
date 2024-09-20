package com.hw3.parkinglot;

public class Vehicle {
    private String vehicleType;
    private String plate;

    public Vehicle(String vehicleType, String plate) {
        this.vehicleType = vehicleType;
        this.plate = plate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getPlate() {
        return plate;
    }
}
