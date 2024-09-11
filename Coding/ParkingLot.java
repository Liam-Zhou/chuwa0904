// Enum for vehicle and parking spot types
enum VehicleType { MOTORCYCLE, CAR, TRUCK }
enum ParkingSpotType { COMPACT, REGULAR, LARGE }

abstract class Vehicle {
    VehicleType type;
    String licensePlate;
    abstract boolean canFitInSpot(ParkingSpot spot);
}

class ParkingSpot {
    ParkingSpotType type;
    boolean isAvailable;
    Vehicle parkedVehicle;

    boolean fit(Vehicle vehicle) {
        // Check if the vehicle fits the spot
    }

    void park(Vehicle vehicle) {
        // Park the vehicle
    }

    void release() {
        // Remove the vehicle from the spot
    }
}

class ParkingLot {
    List<ParkingSpot> compactSpots;
    List<ParkingSpot> regularSpots;
    List<ParkingSpot> largeSpots;

    ParkingSpot findSpot(Vehicle vehicle) {
        // Find an available spot
    }

    boolean parkVehicle(Vehicle vehicle) {
        // Park the vehicle in an available spot
    }

    void releaseSpot(ParkingSpot spot) {
        // Release the spot when the vehicle leaves
    }
}
