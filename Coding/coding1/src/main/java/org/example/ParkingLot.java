package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingLevel> levels;

    public ParkingLot(int numLevels, int spotsPerLevel) {
        levels = new ArrayList<>(numLevels);
        for (int i = 0; i < numLevels; i++) {
            levels.add(new ParkingLevel(i, spotsPerLevel));  // Initialize levels
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                return true;
            }
        }
        return false;  // No space available in the entire parking lot
    }

    public void removeVehicle(int levelNumber, int spotNumber) {
        levels.get(levelNumber).removeVehicle(spotNumber);
    }
}
