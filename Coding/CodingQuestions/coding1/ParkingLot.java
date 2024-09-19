package Coding.CodingQuestions.coding1;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> spots;

    public ParkingLot(int smallSpots, int compactSpots, int largeSpots) {
        spots = new ArrayList<>();
        for (int i = 0; i < smallSpots; i++) {
            spots.add(new ParkingSpot(VehicleSize.SMALL));
        }
        for (int i = 0; i < compactSpots; i++) {
            spots.add(new ParkingSpot(VehicleSize.COMPACT));
        }
        for (int i = 0; i < largeSpots; i++) {
            spots.add(new ParkingSpot(VehicleSize.LARGE));
        }
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.fit(vehicle)) {
                return spot;
            }
        }
        return null;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAvailableSpot(vehicle);
        if (spot != null) {
            spot.park(vehicle);
            ParkingTicket ticket = new ParkingTicket();
            System.out.println("Vehicle parked: " + vehicle.getLicense() + " in a " + vehicle.getSize() + " spot.");
            return ticket;
        } else {
            System.out.println("No available spot for vehicle: " + vehicle.getLicense());
            return null;
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.getCurrentVehicle() == vehicle) {
                spot.leave();
                System.out.println("Vehicle removed: " + vehicle.getLicense());
                break;
            }
        }
    }

    public void displayAvailableSpots() {
        long availableSpots = spots.stream().filter(ParkingSpot::isAvailable).count();
        System.out.println("Available spots: " + availableSpots);
    }
}
