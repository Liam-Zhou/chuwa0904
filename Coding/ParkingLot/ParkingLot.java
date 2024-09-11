package ParkingLot;
import java.util.List;
import java.util.ArrayList;

class ParkingLot {
    private final List<ParkingSpot> spots;

    public ParkingLot() {
        this.spots = new ArrayList<>();
    }
    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.getType() == vehicle.getType() && spot.isAvailable()) {
                spot.setAvailable(false);
                return true;
            }
        }
        System.out.println("No parking spot available for " + vehicle.getType());
        return false;
    }
}