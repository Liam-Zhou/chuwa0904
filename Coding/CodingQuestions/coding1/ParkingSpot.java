package Coding.CodingQuestions.coding1;

public class ParkingSpot {
    private VehicleSize spotSize;
    private Vehicle currentVehicle;

    public ParkingSpot(VehicleSize spotSize) {
        this.spotSize = spotSize;
        this.currentVehicle = null;
    }

    public boolean fit(Vehicle vehicle) {
        return currentVehicle == null && vehicle.getSize().ordinal() <= spotSize.ordinal();
    }

    public void park(Vehicle vehicle) {
        currentVehicle = vehicle;
    }

    public void leave() {
        currentVehicle = null;
    }

    public boolean isAvailable() {
        return currentVehicle == null;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}
