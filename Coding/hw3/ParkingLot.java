import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> spots;

    public ParkingLot(int numCars, int numMotorcycles, int numTrucks) {
        spots = new ArrayList<>();
        for (int i = 0; i < numCars; i++) {
            spots.add(new ParkingSpot(VehicleType.CAR));
        }
        for (int i = 0; i < numMotorcycles; i++) {
            spots.add(new ParkingSpot(VehicleType.MOTORCYCLE));
        }
        for (int i = 0; i < numTrucks; i++) {
            spots.add(new ParkingSpot(VehicleType.TRUCK));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && spot.getSpotType() == vehicle.getType()) {
                return spot.parkVehicle(vehicle);
            }
        }
        System.out.println("No available spots for " + vehicle.getType());
        return false;
    }

    public void removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.getVehicle() == vehicle) {
                spot.removeVehicle();
                System.out.println(vehicle.getLicensePlate() + " has left the parking lot.");
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }
}
