class ParkingLevel {
    private ParkingSpot[] spots;
    private int availableSpots;

    public ParkingLevel(int numberOfSpots) {
        spots = new ParkingSpot[numberOfSpots];
        // Initialize spots
        for (int i = 0; i < numberOfSpots; i++) {
            if (i < numberOfSpots / 3) {
                spots[i] = new ParkingSpot(VehicleSize.MOTORCYCLE, i);  // Motorcycle spots
            } else if (i < 2 * numberOfSpots / 3) {
                spots[i] = new ParkingSpot(VehicleSize.COMPACT, i);  // Compact spots
            } else {
                spots[i] = new ParkingSpot(VehicleSize.LARGE, i);  // Large spots
            }
        }
        availableSpots = numberOfSpots;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && vehicle.canFitInSpot(spot)) {
                spot.parkVehicle(vehicle);
                availableSpots--;
                return true;
            }
        }
        return false;  // No available spot
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (!spot.isAvailable() && spot.vehicle.equals(vehicle)) {
                spot.removeVehicle();
                availableSpots++;
                return;
            }
        }
    }

    public int getAvailableSpots() {
        return availableSpots;
    }
}
