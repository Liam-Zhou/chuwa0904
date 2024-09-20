package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
    private List<ParkingSpot> spots;
    private int levelNumber;

    public ParkingLevel(int levelNumber, int numSpots) {
        this.levelNumber = levelNumber;
        spots = new ArrayList<>(numSpots);
        // Initialize parking spots with different sizes (example)
        for (int i = 0; i < numSpots / 3; i++) {
            spots.add(new ParkingSpot(VehicleSize.COMPACT, i));  // Compact spots
        }
        for (int i = numSpots / 3; i < (2 * numSpots) / 3; i++) {
            spots.add(new ParkingSpot(VehicleSize.REGULAR, i));  // Regular spots
        }
        for (int i = (2 * numSpots) / 3; i < numSpots; i++) {
            spots.add(new ParkingSpot(VehicleSize.LARGE, i));  // Large spots
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.canFitVehicle(vehicle)) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;  // No spot available
    }

    public void removeVehicle(int spotNumber) {
        spots.get(spotNumber).removeVehicle();  // Free up a spot
    }
}
