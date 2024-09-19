class ParkingLot {
    private String lotId;
    private List<ParkingLevel> levels;

    public ParkingLot(String lotId, int numLevels, int spotsPerLevel) {
        this.lotId = lotId;
        this.levels = new ArrayList<>();
        
        for (int i = 0; i < numLevels; i++) {
            levels.add(new ParkingLevel(i, spotsPerLevel));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                return true;
            }
        }
        System.out.println("No parking spot available for vehicle: " + vehicle.getLicensePlate());
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            level.removeVehicle(vehicle);
        }
    }

    public int getTotalAvailableSpots() {
        int totalSpots = 0;
        for (ParkingLevel level : levels) {
            totalSpots += level.getAvailableSpots();
        }
        return totalSpots;
    }
}
