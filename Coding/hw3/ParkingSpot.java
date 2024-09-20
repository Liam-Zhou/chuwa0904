public class ParkingSpot {
    private Vehicle vehicle;
    private final VehicleType spotType;

    public ParkingSpot(VehicleType spotType) {
        this.spotType = spotType;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (this.spotType == vehicle.getType() && isAvailable()) {
            this.vehicle = vehicle;
            return true;
        }
        return false;
    }

    public void removeVehicle() {
        this.vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleType getSpotType() {
        return spotType;
    }
}
