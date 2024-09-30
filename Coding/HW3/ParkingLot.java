class ParkingLot {
    private ParkingLevel[] levels;

    public ParkingLot(int numLevels, int spotsPerLevel) {
        levels = new ParkingLevel[numLevels];
        for (int i = 0; i < numLevels; i++) {
            levels[i] = new ParkingLevel(spotsPerLevel);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                System.out.println(vehicle.getType() + " parked.");
                return true;
            }
        }
        System.out.println("No available spots for " + vehicle.getType());
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            level.removeVehicle(vehicle);
            System.out.println(vehicle.getType() + " removed from parking.");
            return;
        }
    }
}
