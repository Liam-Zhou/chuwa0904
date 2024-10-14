class ParkingLot {
    private ParkingLevel[] levels;

    public ParkingLot(int numberOfLevels, int spotsPerLevel) {
        levels = new ParkingLevel[numberOfLevels];
        for (int i = 0; i < numberOfLevels; i++) {
            levels[i] = new ParkingLevel(spotsPerLevel);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                return true;  // Successfully parked
            }
        }
        return false;  // No available spots
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            level.removeVehicle(vehicle);
        }
    }

    public void displayAvailableSpots() {
        for (int i = 0; i < levels.length; i++) {
            System.out.println("Level " + i + " has " + levels[i].getAvailableSpots() + " available spots.");
        }
    }
}
