enum ParkingSpotType {
    SMALL, MEDIUM, LARGE
}

class ParkingSpot {
    private String spotId;
    private ParkingSpotType spotType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(String spotId, ParkingSpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.isOccupied = false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        if (this.isOccupied) return false;
        if (vehicle.getType() == VehicleType.BIKE && spotType == ParkingSpotType.SMALL) return true;
        if (vehicle.getType() == VehicleType.CAR && (spotType == ParkingSpotType.MEDIUM || spotType == ParkingSpotType.LARGE)) return true;
        return false;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String getSpotId() {
        return spotId;
    }
}
