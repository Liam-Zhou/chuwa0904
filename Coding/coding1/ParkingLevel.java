class ParkingLevel {
    private int levelId;
    private List<ParkingSpot> spots;

    public ParkingLevel(int levelId, int numSpots) {
        this.levelId = levelId;
        this.spots = new ArrayList<>();
        
        // Create parking spots of different sizes
        for (int i = 0; i < numSpots / 2; i++) {
            spots.add(new ParkingSpot("S" + i, ParkingSpotType.SMALL));  // Half are small for bikes
        }
        for (int i = numSpots / 2; i < numSpots; i++) {
            spots.add(new ParkingSpot("M" + i, ParkingSpotType.MEDIUM)); // Half are medium for cars
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.canFitVehicle(vehicle)) {
                spot.parkVehicle(vehicle);
                System.out.println("Vehicle parked in spot: " + spot.getSpotId());
                return true;
            }
        }
        System.out.println("No spot available for vehicle: " + vehicle.getLicensePlate());
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isOccupied() && spot.canFitVehicle(vehicle)) {
                spot.removeVehicle();
                System.out.println("Vehicle removed from spot: " + spot.getSpotId());
                break;
            }
        }
    }

    public int getAvailableSpots() {
        int count = 0;
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                count++;
            }
        }
        return count;
    }
}
