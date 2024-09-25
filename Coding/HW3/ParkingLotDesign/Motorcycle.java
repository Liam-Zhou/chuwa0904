// Motorcycle class
class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleSize.MOTORCYCLE, 1);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return true;  // Motorcycle can fit in any spot
    }
}
