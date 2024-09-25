class ParkingSpot {
    private Vehicle vehicle;  // Vehicle parked in this spot
    private VehicleSize spotSize;
    private int spotNumber;

    public ParkingSpot(VehicleSize spotSize, int spotNumber) {
        this.spotSize = spotSize;
        this.spotNumber = spotNumber;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public VehicleSize getSize() {
        return spotSize;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void removeVehicle() {
        this.vehicle = null;
    }
}
