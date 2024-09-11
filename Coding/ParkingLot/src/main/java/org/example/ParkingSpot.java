package org.example;

public class ParkingSpot {
    private String id;
    private ParkingSpotType spotType;
    private boolean isAvailable;
    private Vehicle parkedVehicle;

    public ParkingSpot(String id, ParkingSpotType type) {
        this.id = id;
        this.spotType = type;
        this.isAvailable = true;
    }

    public String getSpotId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public ParkingSpotType getSpotType() {
        return spotType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return switch (vehicle.getVehicleType()) {
            case BIKE ->
                    spotType == ParkingSpotType.SMALL || spotType == ParkingSpotType.MEDIUM || spotType == ParkingSpotType.LARGE;
            case CAR -> spotType == ParkingSpotType.LARGE || spotType == ParkingSpotType.MEDIUM;
            case TRUCK -> spotType == ParkingSpotType.LARGE;
            default -> false;
        };
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isAvailable = false;

    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isAvailable = true;
    }


}
