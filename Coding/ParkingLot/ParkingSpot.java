package ParkingLot;
abstract class ParkingSpot {
    private final String spotId;
    private final VehicleType type;
    private boolean isAvailable;

    public ParkingSpot(String spotId, VehicleType type) {
        this.spotId = spotId;
        this.type = type;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public VehicleType getType() {
        return type;
    }

    public String getSpotId() {
        return spotId;
    }
}

class CarSpot extends ParkingSpot {
    public CarSpot(String spotId) {
        super(spotId, VehicleType.CAR);
    }
}

class BikeSpot extends ParkingSpot {
    public BikeSpot(String spotId) {
        super(spotId, VehicleType.BIKE);
    }
}
