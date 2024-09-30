class ParkingSpot {
    private Vehicle vehicle;
    private int spotSize;

    public ParkingSpot(int size) {
        this.spotSize = size;
    }

    public boolean fit(Vehicle vehicle) {
        return this.vehicle == null && vehicle.getSize() <= this.spotSize;
    }

    public void assignVehicle(Vehicle vehicle) {
        if (fit(vehicle)) {
            this.vehicle = vehicle;
        }
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }
}
