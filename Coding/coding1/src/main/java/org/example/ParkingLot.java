package coding1.src.main.java.org.example;

public class ParkingLot {
    private ParkingSpot[] spots;

    public ParkingLot(int numberOfSpots) {
        spots = new ParkingSpot[numberOfSpots];
        for (int i = 0; i < spots.length; i++) {
            spots[i] = new ParkingSpot();
        }
    }

    public boolean canParkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                spot.markAsOccupied();
                return true;
            }
        }
        return false;
    }

    public boolean canRemoveVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied()) {
                spot.markAsUnoccupied();
                return true;
            }
        }
        return false;
    }

}
