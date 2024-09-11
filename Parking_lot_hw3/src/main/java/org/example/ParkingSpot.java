package org.example;

public abstract class ParkingSpot {
    private boolean isAvailable = true;
    private Vehicle  vehicle;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void park (Vehicle vehicle) {
        this.vehicle = vehicle;
        isAvailable = false;
    }

    public void leave () {
        this.vehicle = null;
        isAvailable = true;
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public abstract boolean canFitVehicle(Vehicle vehicle);
}
