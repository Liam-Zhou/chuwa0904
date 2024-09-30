class ParkingLevel {
    private ParkingSpot[] spots;

    public ParkingLevel(int numberOfSpots) {
        spots = new ParkingSpot[numberOfSpots];
        for (int i = 0; i < numberOfSpots; i++) {
            if (i < numberOfSpots / 3) {
                spots[i] = new ParkingSpot(3); // Large spots for trucks
            } else if (i < numberOfSpots / 2) {
                spots[i] = new ParkingSpot(1); // Small spots for motorcycles
            } else {
                spots[i] = new ParkingSpot(1); // Medium spots for cars
            }
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.fit(vehicle)) {
                spot.assignVehicle(vehicle);
                return true;
            }
        }
        return false; // No available spot
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.fit(vehicle)) {
                spot.removeVehicle();
                break;
            }
        }
    }
}
