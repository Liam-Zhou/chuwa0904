// Enum for VehicleSize
enum VehicleSize {
    MOTORCYCLE, COMPACT, LARGE
}

// Abstract Vehicle class
abstract class Vehicle {
    protected String licensePlate;
    protected VehicleSize size;
    protected int spotsNeeded;

    public Vehicle(String licensePlate, VehicleSize size, int spotsNeeded) {
        this.licensePlate = licensePlate;
        this.size = size;
        this.spotsNeeded = spotsNeeded;
    }

    public VehicleSize getSize() {
        return size;
    }

    public int getSpotsNeeded() {
        return spotsNeeded;
    }

    public abstract boolean canFitInSpot(ParkingSpot spot);  // Abstract method for checking spot fit
}
