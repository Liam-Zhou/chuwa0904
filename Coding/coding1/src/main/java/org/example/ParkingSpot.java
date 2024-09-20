package org.example;

public class ParkingSpot {
    private VehicleSize size;  // Size of the parking spot (Compact, Regular, Large)
    private Vehicle currentVehicle;  // The vehicle that is currently parked in the spot
    private int spotNumber;  // Identifier for the spot

    public ParkingSpot(VehicleSize size, int spotNumber) {
        this.size = size;
        this.spotNumber = spotNumber;
    }

    public boolean isAvailable() {
        return currentVehicle == null;  // Spot is available if no vehicle is parked
    }

    public VehicleSize getSize() {
        return size;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
    }

    public void removeVehicle() {
        this.currentVehicle = null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return vehicle.canFitInSpot(this);  // Check if the vehicle can fit in the spot
    }
}
