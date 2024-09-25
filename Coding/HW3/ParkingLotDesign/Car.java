// Car class
class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleSize.COMPACT, 1);
    }

    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize() == VehicleSize.COMPACT || spot.getSize() == VehicleSize.LARGE;
    }
}
