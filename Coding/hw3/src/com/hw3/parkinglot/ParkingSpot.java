package com.hw3.parkinglot;

public class ParkingSpot {
    private String spotType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(String spotType, boolean isOccupied) {
        this.spotType = spotType;
        this.isOccupied = isOccupied;
    }

    public boolean park(Vehicle vehicle) {
        if (!isOccupied && this.spotType.equalsIgnoreCase(vehicle.getVehicleType())) {
            this.vehicle = vehicle;
            this.isOccupied = true;
            return true;
        }
        return false;
    }

    public boolean unpark() {
        if (isOccupied) {
            this.vehicle = null;
            this.isOccupied = false;
            return true;
        }
        return false;
    }

    public String getSpotType() {
        return spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return  "spotType='" + spotType + '\'' +
                ", isOccupied=" + isOccupied +
                ", vehicleType=" + vehicle.getVehicleType() +
                ", vehiclePlate=" + vehicle.getPlate();
    }
}
